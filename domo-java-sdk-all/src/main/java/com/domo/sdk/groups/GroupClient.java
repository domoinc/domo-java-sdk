package com.domo.sdk.groups;

import com.domo.sdk.groups.model.Group;
import com.domo.sdk.groups.model.UpdateGroupRequest;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public class GroupClient {
    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String URL_BASE ="v1/groups";

    /**
     * Group Client
     * Programmatically manage Domo User Groups
     * Docs: https://developer.domo.com/docs/domo-apis/group-apis
     */

    public GroupClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    /**
     * Create a Group
     * @param group the new Group specification
     * @return the new Group metadata
     */
    public Group create( Group group) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .build();

        return transport.postJson(url, group, Group.class);
    }

    /**
     * Get a Group
     * @param groupId the Group id
     * @return the Group metadata, if the Group exists
     */
    public Group get(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        return transport.getJson(url, Group.class);
    }

    /**
     * List Groups
     * @param limit the result set limit
     * @param offset the result set offset for pagination purposes
     * @return a list of Groups
     */
    public List<Group> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<Group>>(){}.getType());
    }

    /**
     * Update a Group
     * @param groupId the Group id
     * @param group the Group update specification
     * @return the updated Group metadata
     */
    public Group update(long groupId, UpdateGroupRequest group) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        return transport.putJson(url, group, Group.class);
    }

    /**
     * Delete a Group
     * @param groupId the Group id
     */
    public void delete(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .build();

        transport.deleteJson(url);
    }

    /**
     * Add a User to a Group
     * @param groupId the Group id
     * @param userId the User id
     */
    public void addUserToGroup(long groupId, long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .addPathSegment("users")
                .addPathSegment(Long.toString(userId))
                .build();

        transport.putJson(url, "", String.class);
    }

    /**
     * List Users in a Group
     * @param groupId the Group id
     * @return the list of Users in the Group, if it exists
     */
    public List<Long> listUsersInGroup(long groupId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .addPathSegment("users")
                .build();

        return transport.getJson(url, new TypeToken<List<Long>>(){}.getType());
    }

    /**
     * Remove a User from a Group
     * @param groupId the Group id
     * @param userId the User id
     */
    public void removeUserFromGroup(long groupId, long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(URL_BASE)
                .addPathSegment(Long.toString(groupId))
                .addPathSegment("users")
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }

}
