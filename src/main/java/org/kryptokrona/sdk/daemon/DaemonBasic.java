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
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.exception.NetworkBlockCountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;

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

    private HostName            hostname;
    private boolean             ssl;
    private boolean             sslDetermined;
    private boolean             isCacheApi;
    private boolean             isCacheApiDetermined;
    private String              feeAddress;
    private double              feeAmount;
    private long                localDaemonBlockCount;
    private long                networkBlockCount;
    private long                peerCount;
    private long                lastKnownHashrate;
    private long                blockCount;
    private boolean             useRawBlocks;
    private Config              config;
    private Instant             lastUpdatedNetworkHeight;
    private Instant             lastUpdatedLocalHeight;
    private boolean             connected;

    private static final Logger logger = LoggerFactory.getLogger(DaemonBasic.class);

    public DaemonBasic(HostName hostname) {
        this.hostname                   = hostname;
        this.ssl                        = !this.hostname.isAddress();
        this.sslDetermined              = true;
        this.isCacheApi                 = false;
        this.isCacheApiDetermined       = false;
        this.feeAddress                 = "";
        this.feeAmount                  = 0.0;
        this.localDaemonBlockCount      = 0;
        this.networkBlockCount          = 0;
        this.peerCount                  = 0;
        this.lastKnownHashrate          = 0;
        this.blockCount                 = 100;
        this.useRawBlocks               = true;
        this.connected                  = true;
    }

    @Override
    public Observable<String> open() throws IOException, NetworkBlockCountException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl(String.format("http://%s/getinfo", this.hostname.toString())));

        // keep persistent HTTP connection.
        HttpHeaders headers = request.getHeaders();
        headers.set("Connection", "keep-alive");
        request.setHeaders(headers);

        if (networkBlockCount == 0) {
            logger.error("Network block count cannot be 0.");
            throw new NetworkBlockCountException("Network block count cannot be 0.");
        }

        //TODO: should we use a while loop here?
        //TODO: set lastUpdated times here?

        return Observable.just(request.execute().parseAsString());
    }

    @Override
    public Observable<Void> updateDaemonInfo() {
        return null;
    }

    @Override
    public Observable<Void> updateFeeInfo() {
        return null;
    }

}
