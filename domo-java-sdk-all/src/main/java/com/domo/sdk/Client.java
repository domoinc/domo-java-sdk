package com.domo.sdk;

import com.domo.sdk.datasets.DataSetClient;
import com.domo.sdk.datasets.PDPClient;
import com.domo.sdk.groups.GroupClient;
import com.domo.sdk.request.Config;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.streams.StreamClient;
import com.domo.sdk.users.UserClient;

/**
 * <p>Threadsafe client for interacting with Domo Public APIs. See <a href="https://developer.domo.com/docs/domo-apis/getting-started">
 *     API docs</a> for complete details.
 * </p>
 * <p><b>Use {@link Client#create(String, String)} to instantiate this client.</b></p>
 *
 * Usage:
 * <pre>
 * {@code Client client = Client.create("<your app id>","<your app secret>");
 *  client.userClient().list(10,0);
 * }
 * </pre>
 */
public class Client {
    private final Config config;

    private final UserClient userClient;
    private final GroupClient groupClient;
    private final DataSetClient dataSetClient;
    private final PDPClient pdpClient;
    private final StreamClient streamDataSetClient;

    private final Transport transport;
    private final UrlBuilder urlBuilder;

    private Client(Config config) {
        this.config = config;
        this.urlBuilder = new UrlBuilder(config);

        this.transport = new Transport(config.okHttpClient());

        this.userClient = new UserClient(urlBuilder, transport);
        this.groupClient = new GroupClient(urlBuilder, transport);
        this.dataSetClient = new DataSetClient(urlBuilder, transport);
        this.pdpClient = new PDPClient(urlBuilder, transport);
        this.streamDataSetClient = new StreamClient(urlBuilder, transport);
    }

    public static Client create(String clientId, String secret) {
        return new Client(Config.with().clientId(clientId)
                                        .clientSecret(secret)
                                        .build()
                );
    }

    public static Client create(Config config) {
        return new Client(config);
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

    public PDPClient pdpClient() {
        return pdpClient;
    }

    public StreamClient streamDataSetClient() {
        return streamDataSetClient;
    }

    public Transport getTransport() {
        return transport;
    }

    public UrlBuilder getUrlBuilder() {
        return urlBuilder;
    }
}
