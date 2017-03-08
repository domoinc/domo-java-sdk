package com.domo.sdk.streams;

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


//    POST /v1/streams HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 328
//    Authorization: bearer <your-valid-oauth-access-token>
//
//    {
//        "dataSet" : {
//        "name" : "Leonhard Euler Party",
//                "description" : "Mathematician Guest List",
//                "schema" : {
//            "columns" : [ {
//                "type" : "STRING",
//                        "name" : "Friend"
//            }, {
//                "type" : "STRING",
//                        "name" : "Attending"
//            } ]
//        }
//    },
//        "updateMethod" : "APPEND"
//    }
    public Group create(Group group) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, group, Group.class);
    }


//    GET /v1/streams/42?fields=all HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public Group get(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        return transport.getJson(url, Group.class);
    }



//    GET /v1/streams?fields=all HTTP/1.1
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


//    PATCH /v1/streams/42 HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 31
//    Authorization: bearer <your-valid-oauth-access-token>
//
//    {
//        "updateMethod" : "REPLACE"
//    }
    public Group update(long userId, Group user) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, Group.class);
    }


//    DELETE /v1/streams/42 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }

//    GET /v1/streams/search?q=dataSource.owner.id:27&fields=all HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    //search


//
//    POST /v1/streams/42/executions HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
//    create execution

//    GET /v1/streams/42/executions/1 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
//    getExecution

//    GET /v1/streams/42/executions?limit=50&offset=0 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
//    listExecutions


//    PUT /v1/streams/42/executions/1/part/1 HTTP/1.1
//    Content-Type: text/csv
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 64
//    Authorization: bearer <your-valid-oauth-access-token>
//
//            "Pythagoras","FALSE"
//            "Alan Turing","TRUE"
//            "George Boole","TRUE"
//    uploadAPart


//    PUT /v1/streams/42/executions/1/commit HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
//    commitStream


//    PUT /v1/streams/42/executions/1/abort HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
//    abortStreamExecution


//    PUT /v1/streams/42/executions/abort HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
//    abortCurrentExecution

}
