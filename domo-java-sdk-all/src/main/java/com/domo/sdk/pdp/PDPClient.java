package com.domo.sdk.pdp;

import com.domo.sdk.groups.Group;
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


//    POST /v1/datasets/4405ff58-1957-45f0-82bd-914d989a3ea3/policies HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 162
//    Authorization: bearer <your-valid-oauth-access-token>
//
//    {
//        "name" : "Only Show Attendees",
//            "filters" : [ {
//        "column" : "Attending",
//                "values" : [ "TRUE" ],
//        "operator" : "EQUALS"
//    } ],
//        "users" : [ 27 ]
//    }
    public Group create(Group group) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, group, Group.class);
    }


//    GET /v1/datasets/4405ff58-1957-45f0-82bd-914d989a3ea3/policies/8 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public Group get(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        return transport.getJson(url, Group.class);
    }



//    GET /v1/datasets/4405ff58-1957-45f0-82bd-914d989a3ea3/policies HTTP/1.1
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


//    PUT /v1/datasets/4405ff58-1957-45f0-82bd-914d989a3ea3/policies/8 HTTP/1.1
//    Content-Type: application/json
//    Accept: application/json
//    Host: api.domo.com
//    Content-Length: 154
//    Authorization: bearer <your-valid-oauth-access-token>
//
//    {
//        "name" : "Not Attending",
//            "filters" : [ {
//        "column" : "Attending",
//                "values" : [ "TRUE" ],
//        "operator" : "EQUALS",
//                "not" : true
//    } ]
//    }
    public Group update(long userId, Group user) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, Group.class);
    }


//    DELETE /v1/datasets/4405ff58-1957-45f0-82bd-914d989a3ea3/policies/8 HTTP/1.1
//    Accept: application/json
//    Host: api.domo.com
//    Authorization: bearer <your-valid-oauth-access-token>
    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }

}
