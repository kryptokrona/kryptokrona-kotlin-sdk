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
    Observable<String> open() throws IOException, NetworkBlockCountException;

    /**
     * Update the daemon info.
     *
     * @return Observable
     */
    Observable<Void> updateDaemonInfo();

    /**
     * Update the fee address and amount.
     *
     * @return Observable
     */
    Observable<Void> updateFeeInfo();
}
