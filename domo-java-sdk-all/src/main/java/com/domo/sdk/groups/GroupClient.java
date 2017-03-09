package com.domo.sdk.groups;

import com.domo.sdk.groups.model.Group;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.users.model.User;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class GroupClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE ="v1/groups";

    public GroupClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    public Group create( Group group) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, group, Group.class);
    }

    public Group get(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        return transport.getJson(url, Group.class);
    }

    public List<Group> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Group>>(){}.getType());
    }

    public Group update(long userId, Group user) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, Group.class);
    }

    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }

    public void addUserToGroup(long groupId, long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .addPathSegment("users")
                .addPathSegment(Long.toString(userId))
                .build();

        transport.putJson(url, "", String.class);
    }

    public List<Long> listUsersInGroup(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .addPathSegment("users")
                .build();

        return transport.getJson(url, new TypeToken<List<User>>(){}.getType());
    }

    public void removeUserFromGroup(long groupId, long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .addPathSegment("users")
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }

}
