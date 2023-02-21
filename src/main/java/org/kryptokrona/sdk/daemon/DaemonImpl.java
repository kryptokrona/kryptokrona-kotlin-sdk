// Copyright (c) 2022-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.sdk.daemon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import inet.ipaddr.HostName;
import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.exception.wallet.WalletException;
import org.kryptokrona.sdk.model.http.*;
import org.kryptokrona.sdk.validator.WalletValidator;
import org.kryptokrona.sdk.model.http.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DaemonImp.java
 * <p>
 * Represents a Daemon for communicating to a node.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class DaemonImpl implements Daemon {

	private WalletSyncResponseData walletSyncResponseData;

	private NodeFee nodeFee;

	private NodeInfo nodeInfo;

	private HostName hostname;

	private long localDaemonBlockCount;

	private long networkBlockCount;

	private long peerCount;

	private long lastKnownHashrate;

	private double blockCount;

	private Config config;

	private Instant lastUpdatedNetworkHeight;

	private Instant lastUpdatedLocalHeight;

	private boolean connected;

	private boolean useRawBlocks;

	private static WalletValidator walletValidator;

	private static final Logger logger = LoggerFactory.getLogger(DaemonImpl.class);

	public DaemonImpl(HostName hostname, boolean useRawBlocks) {
		this.hostname = hostname;
		this.localDaemonBlockCount = 0;
		this.networkBlockCount = 0;
		this.peerCount = 0;
		this.lastKnownHashrate = 0;
		this.blockCount = 100;
		this.connected = true;
		this.useRawBlocks = useRawBlocks;
	}

	@Override
	public void init() throws IOException, NodeDeadException {
		daemonReachable().subscribe(status -> logger.info("Initializing Daemon."));

		Observable.merge(updateDaemonInfo(), updateFeeInfo())
			.subscribe(result -> {
				if (networkBlockCount == 0) {
					throw new NetworkBlockCountException();
				}
			});
	}

	@Override
	public Observable<Void> updateDaemonInfo() throws NodeDeadException {
		try {
			getRequest("info").subscribe(response -> {
				var objectMapper = new ObjectMapper();
				var reader = new StringReader(response.asString());
				nodeInfo = objectMapper.readValue(reader, NodeInfo.class);

				logger.info("Node information, updated.");
			});
		} catch (IOException e) {
			logger.error("Failed to update daemon info: " + e);
			var diff1 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;
			var diff2 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;

			if (diff1 > Config.MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL || diff2 > Config.MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL) {
				throw new NodeDeadException();
			}
		}

		if (localDaemonBlockCount != nodeInfo.getHeight() || networkBlockCount != nodeInfo.getNetworkHeight()) {
			lastUpdatedNetworkHeight = Instant.now();
			lastUpdatedLocalHeight = Instant.now();
		} else {
			var diff1 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;
			var diff2 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;

			if (diff1 > Config.MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL || diff2 > Config.MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL) {
				throw new NodeDeadException();
			}
		}

		localDaemonBlockCount = nodeInfo.getHeight();
		networkBlockCount = nodeInfo.getNetworkHeight();

		if (networkBlockCount > 0) {
			networkBlockCount--;
		}

		peerCount = nodeInfo.getIncomingConnectionsCount() + nodeInfo.getOutgoingConnectionsCount();
		lastKnownHashrate = nodeInfo.getHashrate();

		return Observable.empty();
	}

	@Override
	public Observable<Void> updateFeeInfo() {
		try {
			getRequest("fee").subscribe(response -> {
				var objectMapper = new ObjectMapper();
				var reader = new StringReader(response.asString());
				NodeFee nodeFeeObj = objectMapper.readValue(reader, NodeFee.class);

				if (!nodeFeeObj.getAddress().equals("")) {
					var integratedAddressesAllowed = false;

					try {
						WalletValidator.validateAddresses(List.of(nodeFeeObj.getAddress()), integratedAddressesAllowed)
							.subscribe();

						if (nodeFeeObj.getAmount() > 0) {
							nodeFee.setAddress(nodeFeeObj.getAddress());
							nodeFee.setAmount(nodeFeeObj.getAmount());
							nodeFee.setStatus(nodeFeeObj.getStatus());
							logger.info("Node fee information, updated.");
						}
					} catch (WalletException e) {
						logger.error("Failed to validate address from daemon fee info: " + e);
					}
				}
			});
		} catch (IOException e) {
			logger.error("Failed to update fee info: " + e);
		}

		return Observable.empty();
	}

	@Override
	public Observable<Map<ArrayList<Block>, Block>> getWalletSyncData(WalletSyncData walletSyncData) {
		var endpoint = useRawBlocks ? "getrawblocks" : "getwalletsyncdata";

		walletSyncData.setBlockCount(blockCount);
		walletSyncData.setSkipCoinbaseTransactions(!Config.SCAN_COINBASE_TRANSACTIONS);

		try {
			postRequest(endpoint, walletSyncData).subscribe(response -> {
				var objectMapper = new ObjectMapper();
				var reader = new StringReader(response.returnContent().asString());
				walletSyncResponseData = objectMapper.readValue(reader, WalletSyncResponseData.class);
				var numberOfBlocks = walletSyncResponseData.getItems().size();

				/* The node is not dead if we're fetching blocks. */
				if (numberOfBlocks >= 0) {
					logger.info(String.format(numberOfBlocks + " blocks from the daemon."));

					if (blockCount != Config.BLOCKS_PER_DAEMON_REQUEST)  {
						blockCount = Math.min(Config.BLOCKS_PER_DAEMON_REQUEST, blockCount * 2);

						logger.info(String.format("Successfully fetched sync data, raising block count to " + blockCount));
					}

					lastUpdatedNetworkHeight = Instant.now();
					lastUpdatedLocalHeight = Instant.now();
				}

				/*if (data.synced && data.topBlock && data.topBlock.height && data.topBlock.hash) {
					if (this.useRawBlocks) {
						return [await this.rawBlocksToBlocks(data.items), data.topBlock];
					} else {
						return [data.items.map(Block.fromJSON), data.topBlock];
					}
				}

				if (this.useRawBlocks) {
					return [await this.rawBlocksToBlocks(data.items), true];
				} else {
					return [data.items.map(Block.fromJSON), true];
				}*/

			});
		} catch (IOException e) {
			logger.error("Failed to get wallet sync data: " + e + " Lowering block count to: " + blockCount);

			blockCount = Math.ceil(blockCount / 4.0);
			var result = Map.of(new ArrayList<Block>(), new Block());
			logger.info("Amount of blocks: " + result.size());

			return Observable.just(result);
		}

		var blocks = walletSyncResponseData.getItems();

		// TODO: temporary we want to actually return data here if everything goes well in the try/catch
		var result = Map.of(blocks, new Block());

		return Observable.just(result);
	}

	// might not work with our current node since we have not implemented this - not sure though
	@Override
	public Observable<Map<String, Integer>> getGlobalIndexesForRange(int startHeight, int endHeight) {
		try {
			// save the data from the get request here
			getRequest("indexes/" + startHeight + "/" + endHeight)
				.subscribe(response -> logger.info(response.asString()));

			// return the indexes here from the data
		} catch (IOException e) {
			logger.error("Failed to get global indexes: " + e);
		}

		return Observable.empty();
	}

	@Override
	public Observable<List<String>> getCancelledTransactions(List<String> transactionHashes) {
		try {
			postRequest("transaction/status", transactionHashes).subscribe(response -> {
				logger.info(response.returnResponse().toString());
			});

			// return data.notFound or empty array
		} catch (IOException e) {
			logger.error("Failed to get transactions status: " + e);
		}

		return Observable.empty();
	}

	@Override
	public Observable<List<Integer>> getRandomOutputsByAmount(RandomOutputsByAmount randomOutputsByAmount) {
		try {
			postRequest("indexes/random", randomOutputsByAmount).subscribe(response -> {
				logger.info(response.returnResponse().toString());
			});
		} catch (IOException e) {
			logger.error("Failed to get random outs: " + e);
		}

        /*const outputs: [number, [number, string][]][] = [];

        for (const output of data) {
            const indexes: [number, string][] = [];

            for (const outs of output.outputs) {
                indexes.push([outs.index, outs.key]);
            }

            *//* Sort by output index to make it hard to determine real one *//*
            outputs.push([output.amount, _.sortBy(indexes, ([index]) => index)]);
        }*/

		return Observable.empty();
	}

	@Override
	public Observable<Map<Boolean, String>> sendTransaction(String rawTransaction) throws IOException {
		postRequest("sendrawtransaction", rawTransaction)
			.subscribe(result -> {
				System.out.println(result);
			});

		return Observable.empty();
	}

	@Override
	public Observable<List<Block>> rawBlocksToBlocks(List<Block> rawBlocks) {
		return null;
	}

	@Override
	public Observable<Content> getRequest(String param) throws IOException {
		var request = Request.get(String.format("http://%s/%s", this.hostname, param))
				.execute().returnContent();

		return Observable.just(request);
	}

	@Override
	public Observable<Response> postRequest(String param, Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringEntity body = new StringEntity(mapper.writeValueAsString(obj));

		var request = Request.post(String.format("http://%s/%s", this.hostname, param));
		request.addHeader("content-type", "application/json");
		request.body(body);

		return Observable.just(request.execute());
	}

	@Override
	public Observable<Response> postRequest(String param) throws IOException {
		var request = Request.post(String.format("http://%s/%s", this.hostname, param));

		return Observable.just(request.execute());
	}

	@Override
	public Observable<Boolean> daemonReachable() throws IOException {
		var request = Request.get(String.format("http://%s/info", this.hostname))
				.execute();

		return Observable.just((request.returnResponse().getCode() == 200));
	}
}
