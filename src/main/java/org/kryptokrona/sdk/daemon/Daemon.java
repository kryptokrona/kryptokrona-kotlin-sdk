package org.kryptokrona.sdk.daemon;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.http.NodeFee;

import java.io.IOException;

public interface Daemon {

    /**
     * Open a Daemon connection.
     *
     * @throws IOException : If no connection
     */
    void init() throws IOException;

    /**
     * Update the daemon info.
     *
     * @return Observable
     */
    Observable<Void> updateNodeInfo() throws IOException;

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
    Observable<String> postRequest();
}
