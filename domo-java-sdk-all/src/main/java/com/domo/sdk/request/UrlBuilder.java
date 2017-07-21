package com.domo.sdk.request;

import okhttp3.HttpUrl;

import java.util.concurrent.atomic.AtomicReference;

public class UrlBuilder {

    private final AtomicReference<Config> config;

    public UrlBuilder(Config config) {
        this.config = new AtomicReference<>(config);
    }

    public UrlBuilder(AtomicReference<Config> configRef) {
        this.config = configRef;
    }

    public HttpUrl.Builder create() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        String prefix = "https";
        if(!config.get().useHttps()){
            prefix = "http";
        }

        HttpUrl url = HttpUrl.parse(prefix+"://"+config.get().getApiHost());

        builder.scheme(url.scheme());
        builder.host(url.host());
        builder.port(url.port());

        return builder;
    }

    public HttpUrl.Builder fromPathSegments(String urlPathSegments) {
        HttpUrl.Builder urlBuilder = create();

        urlBuilder.addPathSegments(urlPathSegments);

        return urlBuilder;
    }
}
