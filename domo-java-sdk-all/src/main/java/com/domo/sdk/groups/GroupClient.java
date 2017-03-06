package com.domo.sdk.groups;

import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class GroupClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;

    public GroupClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }




//    Get – Get information about a specified group
    //GET /v1/groups/876655018 HTTP/1.1
    //response
//{
//    "id" : 876655018,
//        "name" : "Groupy Group",
//        "active" : true,
//        "memberCount" : 2,
//        "creatorId" : "87659738",
//        "default" : false
//}



//    List – Get a list of all groups
    //GET /v1/groups?offset=20&limit=10 HTTP/1.1
//list of groups


//    Update – Update information for an existing group
    //PUT /v1/groups/876655018 HTTP/1.1
//{
//    "name" : "Groupy McGroup",
//        "active" : true,
//        "default" : false
//}
//update membercount?

//    Delete – Delete a group
//    DELETE /v1/groups/876655018 HTTP/1.1



//    Add Group – Add a user to a group
    //PUT /v1/groups/876655018/users/27 HTTP/1.1



//    List Users – List the users in a group
//    GET /v1/groups/876655018/users HTTP/1.1 (limit, offset)
// return longs

//    Remove Group – Remove a user from a group
//DELETE /v1/groups/876655018/users/27 HTTP/1.1



    public Group create(Group group) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/groups")
                .build();

        return transport.postJson(url, group, Group.class);
    }

    //above finished

    public List<Group> get(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.getJson(url, Group.class);
    }

    public List<Group> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Group>>(){}.getType());
    }


    public Group update(long userId, Group user) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, Group.class);
    }


    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments("/v1/users")
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }





}
