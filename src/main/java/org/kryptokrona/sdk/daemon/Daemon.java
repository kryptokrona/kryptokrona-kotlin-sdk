package org.kryptokrona.sdk.daemon;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;

/**
 * Daemon.java
 *
 * Represents a Daemon for communicating to a node.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Daemon {

    private String hostname;
    private int port;

    public Daemon(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    Observable<String> init() throws IOException {
        HttpRequestFactory requestFactory
                = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl(String.format("http://%s:%s/getinfo", this.hostname, this.port)));
        return Observable.just(request.execute().parseAsString());
        //TODO: Should perhaps return a custom exception to describe more what the error could be about.
    }

}
