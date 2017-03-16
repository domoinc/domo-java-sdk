package com.domo.sdk.request;

import okhttp3.HttpUrl;

public class UrlBuilder {

    private final Config config;

    public UrlBuilder(Config config) {
        this.config = config;
    }

    public HttpUrl.Builder create() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        String prefix = "https";
        if(!config.useHttps()){
            prefix = "http";
        }

        HttpUrl url = HttpUrl.parse(prefix+"://"+config.getApiHost());

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
