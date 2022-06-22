package org.kryptokrona.sdk.daemon;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.http.FeeInfo;

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
    Observable<Void> updateDaemonInfo() throws IOException;

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
    Observable<FeeInfo> getNodeFee();

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
