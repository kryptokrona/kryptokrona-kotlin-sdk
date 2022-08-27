package org.kryptokrona.sdk.daemon;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.model.block.Block;
import org.kryptokrona.sdk.model.block.RawBlock;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.model.http.NodeFee;
import org.kryptokrona.sdk.model.http.RandomOutputsByAmount;
import org.kryptokrona.sdk.model.http.WalletSyncData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Daemon {

    /**
     * Open a Daemon connection.
     *
     * @throws IOException : If no connection
     */
    void init() throws IOException, NetworkBlockCountException, NodeDeadException;

    /**
     * Update the daemon info.
     *
     * @return Observable
     */
    Observable<Void> updateDaemonInfo() throws IOException, NodeDeadException;

    /**
     * Update the fee address and amount.
     *
     * @return Observable
     */
    Observable<Void> updateFeeInfo() throws IOException;

    /**
     * Get the node fee.
     *
     * @return Observable
     */
    Observable<NodeFee> getNodeFee();

    /**
     * Gets blocks from the daemon. Blocks are returned starting from the last
     * known block hash (if higher than the startHeight/startTimestamp).
     *
     * @param walletSyncData : WalletSyncData
     * @return Observable
     */
    Observable<Map<Integer, Boolean>> getWalletSyncData(WalletSyncData walletSyncData) throws IOException;

    /**
     * Returns a mapping of transaction hashes to global indexes.
     *
     * Get global indexes for the transactions in the range
     * [startHeight, endHeight]
     *
     * @param startHeight : List
     * @param endHeight : int
     * @return Observable
     */
    Observable<Map<String, Integer>> getGlobalIndexesForRange(int startHeight, int endHeight) throws IOException;

    /**
     * Returns all cancelled transactions.
     *
     * @param transactionHashes : List
     * @return Observable
     */
    Observable<List<String>> getCancelledTransactions(List<String> transactionHashes);

    /**
     * Gets random outputs for the given amounts. requestedOuts per. Usually mixin+1.
     *
     * Returns an array of amounts to global indexes and keys. There
     * should be requestedOuts indexes if the daemon fully fulfilled
     * our request.
     *
     * @param amounts : List
     * @param requestedOuts : int
     * @return Observable
     */
    Observable<List<Integer>> getRandomOutputsByAmount(RandomOutputsByAmount randomOutputsByAmount);

    /**
     * Sending a transaction.
     *
     * @param rawTransaction : String
     * @return Observable
     */
    Observable<Void> sendTransaction(String rawTransaction);

    /**
     * Convert raw blocks to blocks.
     *
     * @param rawBlocks : List
     * @return Observable
     */
    Observable<List<Block>> rawBlocksToBlocks(List<RawBlock> rawBlocks);

    /**
     * Make a GET request.
     *
     * @param param : String
     * @return Observable
     */
    Observable<String> getRequest(String param) throws IOException;

    /**
     * Make a POST request.
     *
     * @return Observable
     */
    Observable<String> postRequest(String param, Object obj) throws IOException;
}
