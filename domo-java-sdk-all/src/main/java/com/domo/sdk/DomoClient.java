package com.domo.sdk;

import com.domo.sdk.datasets.DataSetClient;
import com.domo.sdk.datasets.PDPClient;
import com.domo.sdk.groups.GroupClient;
import com.domo.sdk.pages.PageClient;
import com.domo.sdk.request.Config;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.streams.StreamClient;
import com.domo.sdk.users.UserClient;

/**
 * <p>Threadsafe client for interacting with Domo Public APIs. See <a href="https://developer.domo.com/docs/domo-apis/getting-started">
 *     API docs</a> for complete details.
 * </p>
 * <p><b>Use {@link DomoClient#create(String, String)} to instantiate this client.</b></p>
 *
 * Usage:
 * <pre>
 * {@code Client client = Client.create("<your app id>","<your app secret>");
 *  client.userClient().list(10,0);
 * }
 * </pre>
 */
public class DomoClient {
    private final Config config;

    private final UserClient userClient;
    private final GroupClient groupClient;
    private final DataSetClient dataSetClient;
    private final PageClient pageClient;
    private final PDPClient pdpClient;
    private final StreamClient streamClient;

    private final Transport transport;
    private final UrlBuilder urlBuilder;

    private DomoClient( Config config) {
        this.config = config;
        this.urlBuilder = new UrlBuilder(config);

        this.transport = new Transport(config.okHttpClient());

        this.userClient = new UserClient(urlBuilder, transport);
        this.groupClient = new GroupClient(urlBuilder, transport);
        this.dataSetClient = new DataSetClient(urlBuilder, transport);
        this.pageClient = new PageClient(urlBuilder, transport);
        this.pdpClient = new PDPClient(urlBuilder, transport);
        this.streamClient = new StreamClient(urlBuilder, transport);
    }

    public static DomoClient create( String clientId, String secret) {
        return new DomoClient(Config.with().clientId(clientId)
                                        .clientSecret(secret)
                                        .build()
                );
    }

    public static DomoClient create( Config config) {
        return new DomoClient(config);
    }

    public Config getConfig() {
        return config;
    }

    public UserClient userClient() {
        return userClient;
    }

    public GroupClient groupClient() {
        return groupClient;
    }

    public DataSetClient dataSetClient() {
        return dataSetClient;
    }

    public PageClient pageClient() {
        return pageClient;
    }

    public PDPClient pdpClient() {
        return pdpClient;
    }

    public StreamClient streamClient() {
        return streamClient;
    }

    public Transport getTransport() {
        return transport;
    }

    public UrlBuilder getUrlBuilder() {
        return urlBuilder;
    }
}
