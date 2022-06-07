package org.kryptokrona.sdk.daemon;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.exception.NodeHostnameException;

import java.io.IOException;

public interface Daemon {

    Observable<String> init() throws IOException;
}
