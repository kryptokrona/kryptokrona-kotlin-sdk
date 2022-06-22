package org.kryptokrona.sdk.daemon;

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
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.http.FeeInfo;
import org.kryptokrona.sdk.http.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
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
    private Type                feeInfoCollectionType;
    private Type                infoCollectionType;
    private FeeInfo             feeInfo;
    private Info                info;
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
        this.feeInfoCollectionType      = new TypeToken<FeeInfo>(){}.getType();
        this.infoCollectionType         = new TypeToken<Info>(){}.getType();
        this.hostname                   = hostname;
        this.localDaemonBlockCount      = 0;
        this.networkBlockCount          = 0;
        this.peerCount                  = 0;
        this.lastKnownHashrate          = 0;
        this.blockCount                 = 100;
        this.connected                  = true;
        this.config                     = new Config();
    }

    @Override
    public void init() throws IOException {
        updateDaemonInfo().subscribe();
        updateFeeInfo().subscribe();
    }

    @Override
    public Observable<Void> updateDaemonInfo() throws IOException {
        getRequest("info").subscribe(json -> {
            // parse json to Info object
            Info infoObj = gson.fromJson(json, infoCollectionType);

            //TODO: add more logic in here (check wallet-backend-js)

            /*Instant diff1 = Instant.now(lastUpdatedNetworkHeight / 1000);
            Instant diff2 = Instant.now(lastUpdatedLocalHeight / 1000);

            if (diff1 > config.getMaxLastUpdatedNetworkHeightInterval() || diff2 > config.getMaxLastUpdatedLocalHeightInterval()) {
                // this.emit('deadnode');
            }*/

            localDaemonBlockCount = infoObj.getHeight();
            networkBlockCount = infoObj.getNetworkHeight();

            if (networkBlockCount > 0) {
                networkBlockCount--;
            }

            peerCount = infoObj.getIncomingConnectionsCount() + infoObj.getOutgoingConnectionsCount();

            lastKnownHashrate = infoObj.getHashrate();
        });

        return Observable.empty();
    }

    @Override
    public Observable<Void> updateFeeInfo() throws IOException {
        getRequest("fee").subscribe(json -> {
            // parse json to FeeInfo object
            FeeInfo feeInfoObj = gson.fromJson(json, feeInfoCollectionType);

            //TODO: add more logic in here (check wallet-backend-js)

            // check if both amount is more than 0 and address is set
            if (feeInfoObj.getAmount() > 0 && !Objects.equals(feeInfoObj.getAddress(), "")) {
                this.feeInfo = feeInfoObj;
            }
        });

        return Observable.empty();
    }

    @Override
    public Observable<FeeInfo> getNodeFee() {
        return Observable.just(feeInfo);
    }

    @Override
    public Observable<String> getRequest(String param) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl(String.format("http://%s/%s", this.hostname.toString(), param)));

        return Observable.just(request.execute().parseAsString());
    }

    @Override
    public Observable<String> postRequest() {
        return null;
    }

}
