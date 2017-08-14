package com.domo.sdk.datasets;

import com.domo.sdk.datasets.model.Policy;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class PDPClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE ="v1/datasets";

    public PDPClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    public Policy create(String dataSetId, Policy policy) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(dataSetId)
                .addPathSegment("policies")
                .build();

        return transport.postJson(url, policy, Policy.class);
    }

    public Policy get(String dataSetId, long policyId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(dataSetId)
                .addPathSegment("policies")
                .addPathSegment(Long.toString(policyId))
                .build();

        return transport.getJson(url, Policy.class);
    }

    public List<Policy> list(String dataSetId, int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(dataSetId)
                .addPathSegment("policies")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Policy>>(){}.getType());
    }


    public Policy update(String dataSetId, long policyId, Policy policy) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(dataSetId)
                .addPathSegment("policies")
                .addPathSegment(Long.toString(policyId))
                .build();

        return transport.putJson(url, policy, Policy.class);
    }


    public void delete(String dataSetId, long policyId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(dataSetId)
                .addPathSegment("policies")
                .addPathSegment(Long.toString(policyId))
                .build();

        transport.deleteJson(url);
    }

}
