package com.domo.sdk.users;

import com.domo.sdk.Client;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.domo.sdk.users.model.CreateUserRequest;
import com.domo.sdk.users.model.User;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * <p>Threadsafe client for performing User related requests. See <a href="https://developer.domo.com/docs/domo-apis/users">
 *     User API</a> docs for complete details.
 * </p>
 * <p><b>Use {@link Client#userClient()} to get a reference to an instance of this client.</b></p>
 *
 * Usage:
 * <pre>
 * {@code Client client = Client.create("<your app id>","<your app secret>");
 *  client.userClient().list(10,0);
 * }
 * </pre>
 */
public class UserClient {
    private static final String USERS_URL = "v1/users";
    private final UrlBuilder urlBuilder;
    private final Transport transport;

    /**
     * Avoid using this constructor directly.
     *
     * @param urlBuilder Config of base URL for requests
     * @param transport Helper for Request and Response processing
     */
    public UserClient(UrlBuilder urlBuilder, Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }


    /**
     * See <a href="https://developer.domo.com/docs/domo-apis/users#API%20-%20Create%20a%20User">Create User</a> Api Docs
     *
     * @param sendInvite should invitaion email be sent to newly invited user
     * @param newUser user to create
     * @return User that was created, including user ID
     */
    public User create( boolean sendInvite, CreateUserRequest newUser) {
        HttpUrl url = urlBuilder.fromPathSegments(USERS_URL)
                .addQueryParameter("sendInvite", Boolean.toString(sendInvite))
                .build();

        return transport.postJson(url, newUser, User.class);
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/users#API%20-%20Get%20a%20User">Get User</a> Api Docs
     *
     * @param userId id of user to fetch
     * @return User that was found
     */
    public User get(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(USERS_URL)
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.getJson(url, User.class);
    }

    /**
     *  See <a href="https://developer.domo.com/docs/domo-apis/users#API%20-%20List%20Users">List Users</a> Api Docs
     *
     * @param limit number of users to fetch
     * @param offset page offset of user result
     * @return List of Users
     */
    public List<User> list(int limit, int offset) {
        HttpUrl url = urlBuilder.fromPathSegments(USERS_URL)
                .addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset))
                .build();

        return transport.getJson(url, new TypeToken<List<User>>(){}.getType());
    }


    /**
     * See <a href="https://developer.domo.com/docs/domo-apis/users#API%20-%20Update%20a%20User">Update User</a> API Docs
     * @param userId id of user to update
     * @param user New user attributes
     * @return User with updated attributes
     */
    public User update(long userId, User user) {
        HttpUrl url = urlBuilder.fromPathSegments(USERS_URL)
                .addPathSegment(Long.toString(userId))
                .build();

        return transport.putJson(url, user, User.class);
    }


    /**
     * See <a href="https://developer.domo.com/docs/domo-apis/users#API%20-%20Delete%20a%20User">Delete User</a> API Docs
     *
     * @param userId id of user to delete
     */
    public void delete(long userId) {
        HttpUrl url = urlBuilder.fromPathSegments(USERS_URL)
                .addPathSegment(Long.toString(userId))
                .build();

        transport.deleteJson(url);
    }





}
