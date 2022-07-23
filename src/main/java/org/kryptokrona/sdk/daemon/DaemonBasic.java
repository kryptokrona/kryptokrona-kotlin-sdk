package org.kryptokrona.sdk.daemon;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import inet.ipaddr.HostName;
import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.model.block.Block;
import org.kryptokrona.sdk.model.block.RawBlock;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.model.http.NodeFee;
import org.kryptokrona.sdk.model.http.NodeInfo;
import org.kryptokrona.sdk.model.http.WalletSyncData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DaemonBasic.java
 *
 * Represents a Daemon for communicating to a node.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class DaemonBasic implements Daemon {

    private Gson                gson;
    private HttpRequestFactory  requestFactory;
    private Type                feeInfoCollectionType;
    private Type                infoCollectionType;
    private NodeFee             nodeFee;
    private NodeInfo            nodeInfo;
    private HostName            hostname;
    private long                localDaemonBlockCount;
    private long                networkBlockCount;
    private long                peerCount;
    private long                lastKnownHashrate;
    private long                blockCount;
    private Config              config;
    private Instant             lastUpdatedNetworkHeight;
    private Instant             lastUpdatedLocalHeight;
    private boolean             connected;

    private static final Logger logger = LoggerFactory.getLogger(DaemonBasic.class);

    public DaemonBasic(HostName hostname) {
        this.gson                       = new Gson();
        this.requestFactory             = new NetHttpTransport().createRequestFactory();
        this.feeInfoCollectionType      = new TypeToken<NodeFee>(){}.getType();
        this.infoCollectionType         = new TypeToken<NodeInfo>(){}.getType();
        this.hostname                   = hostname;
        this.localDaemonBlockCount      = 0;
        this.networkBlockCount          = 0;
        this.peerCount                  = 0;
        this.lastKnownHashrate          = 0;
        this.blockCount                 = 100;
        this.connected                  = true;
    }

    @Override
    public void init() throws IOException, NetworkBlockCountException {
        logger.info("Initializing Daemon.");

        Observable.merge(updateNodeInfo(), updateFeeInfo()).subscribe(result -> {
            if (networkBlockCount == 0) {
                throw new NetworkBlockCountException();
            }
        });
    }

    @Override
    public Observable<Void> updateNodeInfo() {
        try {
            getRequest("info").subscribe(json -> {
                // parse json to Info object
                nodeInfo = gson.fromJson(json, infoCollectionType);

                localDaemonBlockCount = nodeInfo.getHeight();
                networkBlockCount = nodeInfo.getNetworkHeight();

                if (localDaemonBlockCount != nodeInfo.getHeight() || networkBlockCount != nodeInfo.getNetworkHeight()) {
                    /*this.emit('heightchange', info.height, info.networkHeight); */

                    lastUpdatedNetworkHeight = Instant.now();
                    lastUpdatedLocalHeight = Instant.now();

                    // emit some data with observable
                } else {
                    var diff1 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;
                    var diff2 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;

                    if (diff1 > Config.MAX_LAST_FETCHED_BLOCK_INTERVAL || diff2 > Config.MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL) {
                        // emit dead node
                        throw new
                    }
                }

                if (nodeInfo.getNetworkHeight() > 0) {
                    nodeInfo.setNetworkHeight(nodeInfo.getNetworkHeight() - 1);
                }

                peerCount = nodeInfo.getIncomingConnectionsCount() + nodeInfo.getOutgoingConnectionsCount();
                lastKnownHashrate = nodeInfo.getHashrate();
                logger.info("Node information, updated.");
            });
        } catch (IOException e) {
            var diff1 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;
            var diff2 = (Instant.now().toEpochMilli() - lastUpdatedNetworkHeight.toEpochMilli()) / 1000;

            if (diff1 > Config.MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL || diff2 > Config.MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL) {
                logger.error("Dead Node");
            }
        }

        return Observable.empty();
    }

    @Override
    public Observable<Void> updateFeeInfo() throws IOException {
        getRequest("fee").subscribe(json -> {
            // parse json to FeeInfo object
            NodeFee nodeFeeObj = gson.fromJson(json, feeInfoCollectionType);

            //TODO: add more logic in here (check wallet-backend-js)

            // check if both amount is more than 0 and address is set
            if (nodeFeeObj.getAmount() > 0 && !Objects.equals(nodeFeeObj.getAddress(), "")) {
                nodeFee = nodeFeeObj;
                logger.info("Node fee information, updated.");
            }
        });

        return Observable.empty();
    }

    @Override
    public Observable<NodeFee> getNodeFee() {
        return Observable.just(nodeFee);
    }

    @Override
    public Observable<Map<Integer, Boolean>> getWalletSyncData(
            WalletSyncData walletSyncData) throws IOException {
        postRequest("/sync/raw", walletSyncData).subscribe(json -> {
            logger.info(json);
        });

        //TODO: should be implemented in WalletSyncronizer

        return null;
    }

    @Override
    public Observable<Map<String, Integer>> getGlobalIndexesForRange(int startHeight, int endHeight) {
        return null;
    }

    @Override
    public Observable<String> getCancelledTransactions(List<String> transactionHashes) {
        return null;
    }

    @Override
    public Observable<List<Integer>> getRandomOutputsByAmount(List<Integer> amounts, int requestedOuts) {
        return null;
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
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl(String.format("http://%s/%s", this.hostname.toString(), param)));

        return Observable.just(request.execute().parseAsString());
    }

    @Override
    public Observable<String> postRequest(String param, Object obj) throws IOException {
        HttpRequest request = requestFactory.buildPostRequest(
                new GenericUrl(String.format("http://%s/%s", this.hostname.toString(), param)),
                ByteArrayContent.fromString("application/json", gson.toJson(obj, new TypeToken<Object>(){}.getType())));

        return Observable.just(request.getHeaders().setContentType("application/json").toString());
    }

}
