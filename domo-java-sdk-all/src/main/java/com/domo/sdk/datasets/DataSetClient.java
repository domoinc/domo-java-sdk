package com.domo.sdk.datasets;

import com.domo.sdk.groups.Group;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class DataSetClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE ="v1/datasets";

    public DataSetClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }


    public DataSet create(CreateDataSetRequest dataSet) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, dataSet, DataSet.class);
    }


//    GET /v1/datasets/08a061e2-12a2-4646-b4bc-20beddb403e3 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public Group get(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        return transport.getJson(url, Group.class);
    }



//    List – List all your DataSets in your Domo Warehouse
//    GET /v1/datasets?sort=name&offset=20&limit=10 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public List<Group> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Group>>(){}.getType());
    }


//    PUT /v1/datasets/317970a1-6a6e-4f70-8e09-44cf5f34cf44 HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 122
//    Authorization: bearer <your-valid-oauth-access-token>
//
//    {
//        "name" : "Leonhard Euler Birthday Bash",
//            "description" : "VIP Guest List",
//            "pdpEnabled" : true
//    }
    public Group update(long userId, Group user) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, Group.class);
    }


    //    Delete – Delete a specified CreateDataSetRequest in your Domo Warehouse
//    DELETE /v1/datasets/317970a1-6a6e-4f70-8e09-44cf5f34cf44 HTTP/1.1
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }

    //    Push – Push data to specified CreateDataSetRequest
//    PUT /v1/datasets/317970a1-6a6e-4f70-8e09-44cf5f34cf44/data HTTP/1.1
//    Content-Type: text/csv
//    Host: api.domo.com
//    Content-Length: 18
//    Authorization: bearer <your-valid-oauth-access-token>
//            1,2,3
//            4,5,6
//            7,8,92




//    Pull – Pull data from a specified CreateDataSetRequest
//    GET /v1/datasets/317970a1-6a6e-4f70-8e09-44cf5f34cf44/data?includeHeader=true&fileName=data-dump.csv HTTP/1.1
//    Accept: text/csv
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    //export


}
