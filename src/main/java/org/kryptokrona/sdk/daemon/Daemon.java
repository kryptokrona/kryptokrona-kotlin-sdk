package org.kryptokrona.sdk.daemon;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.exception.NetworkBlockCountException;

import java.io.IOException;

public interface Daemon {

    /**
     * Open a Daemon connection.
     *
     * @throws IOException : If no connection
     * @throws NetworkBlockCountException : If invalid network block count
     * @return Observable
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

    Observable<String> getRequest(String param) throws IOException;

    Observable<String> postRequest();
}
