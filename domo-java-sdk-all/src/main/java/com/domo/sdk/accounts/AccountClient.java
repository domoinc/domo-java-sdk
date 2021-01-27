package com.domo.sdk.accounts;

import com.domo.sdk.accounts.model.Account;
import com.domo.sdk.accounts.model.AccountShare;
import com.domo.sdk.accounts.model.AccountType;
import com.domo.sdk.request.Transport;
import com.domo.sdk.request.UrlBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;

import java.util.List;

public final class AccountClient {

    private final UrlBuilder urlBuilder;
    private final Transport transport;
    private static final String ACCOUNTS_URL = "v1/accounts";
    private static final String ACCOUNT_TYPES_URL = "v1/account-types";

    public AccountClient(final UrlBuilder urlBuilder, final Transport transport) {
        this.urlBuilder = urlBuilder;
        this.transport = transport;
    }

    /**
     * Create an Account
     *
     * @param account the Account to create
     * @return the created Account
     */
    public Account create(final Account account) {
        HttpUrl url = urlBuilder.fromPathSegments(ACCOUNTS_URL)
                .build();

        return transport.postJson(url, account, Account.class);
    }

    /**
     * Retrieve an Account
     *
     * @param accountId the ID of the Account to retrieve
     * @return the Account, if it exists
     */
    public Account get(String accountId) {
        HttpUrl url = urlBuilder.fromPathSegments(ACCOUNTS_URL)
                .addPathSegment(accountId)
                .build();

        return transport.getJson(url, Account.class);
    }

    /**
     * Update an Account
     *
     * @param accountId the ID of the Account to update
     * @param account the Account to update
     */
    public void update(final String accountId, final Account account) {
        HttpUrl url = urlBuilder.fromPathSegments(ACCOUNTS_URL)
                .addPathSegment(accountId)
                .build();

        transport.patchJson(url, account);
    }

    /**
     * Delete an Account
     *
     * @param accountId the ID of the Account to delete
     */
    public void delete(String accountId) {
        HttpUrl url = urlBuilder.fromPathSegments(ACCOUNTS_URL)
                .addPathSegment(accountId)
                .build();

        transport.deleteJson(url);
    }

    /**
     * List Accounts
     *
     * @param limit  the result set limit. The max is 50; use offset pagination to retrieve more Accounts
     * @param offset the result set offset for pagination purposes
     * @return a list of Accounts
     */
    public List<Account> list(int limit, int offset) {

        return list(null, limit, offset);

    }

    /**
     * List Accounts
     *
     * @param sort   the Account field property by which to sort the list
     * @param limit  the result set limit. The max is 50; use offset pagination to retrieve more Accounts
     * @param offset the result set offset for pagination purposes
     * @return a list of Accounts
     */
    public List<Account> list(String sort, int limit, int offset) {

        HttpUrl url = createPaginatedRequestUrl(ACCOUNTS_URL, sort, limit, offset);

        return transport.getJson(url, new TypeToken<List<Account>>() {
        }.getType());
    }

    /**
     * List Account Types
     *
     * @param limit  the result set limit. The max is 50; use offset pagination to retrieve more Account Types
     * @param offset the result set offset for pagination purposes
     * @return the account types
     */
    public List<AccountType> listAccountTypes(final int limit, final int offset) {

        return listAccountTypes(null, limit, offset);

    }

    /**
     * List Account Types
     *
     * @param sort   the AccountType field by which to sort the list
     * @param limit  the result set limit. The max is 50; use offset pagination to retrieve more Account Types
     * @param offset the result set offset for pagination purposes
     * @return the account types
     */
    public List<AccountType> listAccountTypes(final String sort, final int limit, final int offset) {

        HttpUrl url = createPaginatedRequestUrl(ACCOUNT_TYPES_URL, sort, limit, offset);

        return transport.getJson(url, new TypeToken<List<AccountType>>() {
        }.getType());
    }

    /**
     * Retrieve an Account Type
     *
     * @param id the ID of the Account Type to retrieve
     * @return the Account Type, if it exists
     */
    public AccountType getAccountType(String id) {
        HttpUrl url = urlBuilder.fromPathSegments(ACCOUNT_TYPES_URL)
                .addPathSegment(id)
                .build();

        return transport.getJson(url, AccountType.class);
    }

    /**
     * Share an Account
     *
     * @param accountId    the ID of the Account to share
     * @param accountShare the way in which this account is to be shared
     */
    public void shareAccount(String accountId, AccountShare accountShare) {
        HttpUrl url = urlBuilder.fromPathSegments(ACCOUNTS_URL)
                .addPathSegment(accountId)
                .addPathSegment("shares")
                .build();

        transport.postJson(url, accountShare);
    }

    private HttpUrl createPaginatedRequestUrl(final String pathSegments, final String sort, final int limit, final int offset) {
        HttpUrl.Builder builder = urlBuilder.fromPathSegments(pathSegments);

        builder.addQueryParameter("limit", Integer.toString(limit))
                .addQueryParameter("offset", Integer.toString(offset));

        if (sort != null) {
            builder.addQueryParameter("sort", sort);
        }

        return builder.build();
    }
}
