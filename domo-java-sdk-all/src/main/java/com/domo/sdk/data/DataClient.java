package com.domo.sdk.data;

import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;

/**
 * Created by clintchecketts on 3/3/17.
 */
public class DataClient {
    private final Transport transport;

    public DataClient(UrlBuilder urlBuilder, Transport transport) {
        this.transport = transport;
    }
}
