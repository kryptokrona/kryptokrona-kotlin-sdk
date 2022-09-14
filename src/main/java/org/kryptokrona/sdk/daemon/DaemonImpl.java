package org.kryptokrona.sdk.daemon;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import inet.ipaddr.HostName;
import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.block.Block;
import org.kryptokrona.sdk.block.RawBlock;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.exception.wallet.WalletException;
import org.kryptokrona.sdk.model.http.*;
import org.kryptokrona.sdk.validator.WalletValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.kryptokrona.sdk.config.Config.*;

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

	private Gson gson;

	private HttpRequestFactory requestFactory;

	private Type feeInfoCollectionType;

	private Type infoCollectionType;

	private Type walletSyncResponseDataType;

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

	private WalletValidator walletValidator;

	private static final Logger logger = LoggerFactory.getLogger(DaemonImpl.class);

	public DaemonImpl(HostName hostname, boolean useRawBlocks) {
		this.gson = new Gson();
		this.requestFactory = new NetHttpTransport().createRequestFactory();
		this.feeInfoCollectionType = new TypeToken<NodeFee>() {
		}.getType();
		this.infoCollectionType = new TypeToken<NodeInfo>() {
		}.getType();
		this.walletSyncResponseDataType = new TypeToken<WalletSyncResponseData>() {
		}.getType();
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

		Observable.merge(updateDaemonInfo(), updateFeeInfo()).subscribe(result -> {
			if (networkBlockCount == 0) {
				throw new NetworkBlockCountException();
			}
		});
	}

	@Override
	public Observable<Void> updateDaemonInfo() throws NodeDeadException {
		try {
			getRequest("info").subscribe(json -> {
				nodeInfo = gson.fromJson(json, infoCollectionType);
				logger.info("Node information, updated.");
			});
		} catch (IOException e) {
			logger.error("Failed to update daemon info: " + e);
			var diff1 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;
			var diff2 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;

			if (diff1 > MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL || diff2 > MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL) {
				throw new NodeDeadException();
			}
		}

		if (localDaemonBlockCount != nodeInfo.getHeight() || networkBlockCount != nodeInfo.getNetworkHeight()) {
			lastUpdatedNetworkHeight = Instant.now();
			lastUpdatedLocalHeight = Instant.now();
		} else {
			var diff1 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;
			var diff2 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;

			if (diff1 > MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL || diff2 > MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL) {
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
			getRequest("fee").subscribe(json -> {
				NodeFee nodeFeeObj = gson.fromJson(json, feeInfoCollectionType);

				var integratedAddressesAllowed = false;

				try {
					walletValidator.validateAddresses(List.of(nodeFee.getAddress()), integratedAddressesAllowed)
							.subscribe();

					if (nodeFeeObj.getAmount() > 0 && !nodeFeeObj.getAddress().equals("")) {
						nodeFee.setAddress(nodeFeeObj.getAddress());
						nodeFee.setAmount(nodeFeeObj.getAmount());
						nodeFee.setStatus(nodeFee.getStatus());
						logger.info("Node fee information, updated.");
					}
				} catch (WalletException e) {
					logger.error("Failed to validate address from daemon fee info: " + e);
				}
			});
		} catch (IOException e) {
			logger.error("Failed to update fee info: " + e);
		}

		return Observable.empty();
	}

	@Override
	public Observable<Map<Integer, Boolean>> getWalletSyncData(WalletSyncData walletSyncData) {
		var endpoint = useRawBlocks ? "sync/raw" : "sync";

		walletSyncData.setBlockCount(blockCount);
		walletSyncData.setSkipCoinbaseTransactions(!SCAN_COINBASE_TRANSACTIONS);

		WalletSyncResponseData walletSyncResponseData;

		try {
			postRequest(endpoint, walletSyncData).subscribe(json -> {
				gson.fromJson(json, walletSyncResponseDataType);
			});
		} catch (IOException e) {
			blockCount = Math.ceil(blockCount / 4.0);
			logger.error("Failed to get wallet sync data: " + e + " Lowering block count to: " + blockCount);
		}

		// the node is not dead if we're fetching blocks.
        /*if (response.length >= 0) {

        }*/

		return null;
	}

	// might not work with our current node since we have not implemented this - not sure though
	@Override
	public Observable<Map<String, Integer>> getGlobalIndexesForRange(int startHeight, int endHeight) {
		try {
			// save the data from the get request here
			getRequest("indexes/" + startHeight + "/" + endHeight)
					.subscribe(logger::info);

			// return the indexes here from the data
		} catch (IOException e) {
			logger.error("Failed to get global indexes: " + e);
		}

		return Observable.empty();
	}

	@Override
	public Observable<List<String>> getCancelledTransactions(List<String> transactionHashes) {
		try {
			postRequest("transaction/status", transactionHashes).subscribe(logger::info);

			// return data.notFound or empty array
		} catch (IOException e) {
			logger.error("Failed to get transactions status: " + e);
		}

		return Observable.empty();
	}

	@Override
	public Observable<List<Integer>> getRandomOutputsByAmount(RandomOutputsByAmount randomOutputsByAmount) {
		try {
			postRequest("indexes/random", randomOutputsByAmount).subscribe(logger::info);
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
	public Observable<Void> sendTransaction(String rawTransaction) {
		//TODO need to check this return type
		return null;
	}

	@Override
	public Observable<List<Block>> rawBlocksToBlocks(List<RawBlock> rawBlocks) {
		return null;
	}

	@Override
	public Observable<String> getRequest(String param) throws IOException {
		var request = requestFactory.buildGetRequest(
				new GenericUrl(String.format("http://%s/%s", this.hostname.toString(), param)));

		return Observable.just(request.execute().parseAsString());
	}

	@Override
	public Observable<String> postRequest(String param, Object obj) throws IOException {
		var request = requestFactory.buildPostRequest(
				new GenericUrl(String.format("http://%s/%s", this.hostname.toString(), param)),
				ByteArrayContent.fromString("application/json", gson.toJson(obj, new TypeToken<Object>() {
				}.getType())));

		return Observable.just(request.getHeaders().setContentType("application/json").toString());
	}

	@Override
	public Observable<Boolean> daemonReachable() throws IOException {
		var request = requestFactory.buildGetRequest(
				new GenericUrl(String.format("http://%s/info", this.hostname.toString())));

		return Observable.just((request.execute()).getStatusCode() == 200);
	}
}
