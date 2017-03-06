package com.domo.sdk.request;

import okhttp3.HttpUrl;

public class UrlBuilder {

    private final Config config;

    public UrlBuilder(Config config) {
        this.config = config;
    }

    public HttpUrl.Builder create() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        if(config.useHttps()){
            builder.scheme("https");
        } else {
            builder.scheme("http");
        }

        builder.host(config.getApiHost());

        return builder;
    }

    public HttpUrl.Builder fromPathSegments(String urlPathSegments) {
        HttpUrl.Builder urlBuilder = create();

        urlBuilder.addPathSegments(urlPathSegments);

        return urlBuilder;
    }
}
