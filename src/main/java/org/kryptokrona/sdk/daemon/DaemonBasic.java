package org.kryptokrona.sdk.daemon;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import inet.ipaddr.HostName;
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
public class DaemonBasic implements Daemon {

    private HostName hostname;

    public DaemonBasic(HostName hostname) {
        this.hostname = hostname;
    }

    @Override
    public Observable<String> init() throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl(String.format("http://%s/getinfo", this.hostname.toString())));

        // keep persistent HTTP connection.
        HttpHeaders headers = request.getHeaders();
        headers.set("Connection", "keep-alive");
        request.setHeaders(headers);

        return Observable.just(request.execute().parseAsString());
    }

}
