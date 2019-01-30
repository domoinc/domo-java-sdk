package com.domo.sdk.accounts;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.accounts.model.Account;
import com.domo.sdk.accounts.model.AccountShare;
import com.domo.sdk.users.UserClient;
import com.domo.sdk.users.model.User;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public final class ShareExample extends ExampleBase {

    @Test
    public void share_account_example() throws IOException {
        UserClient userClient = client.userClient();

        List<User> users = userClient.list(50, 0);

        AccountClient accountClient = client.accountClient();

        List<Account> accounts = accountClient.list(50, 0);

        Account accountToShare = accounts.iterator().next();
        User userToShareWith = users.iterator().next();

        accountClient.shareAccount(accountToShare.getId(), new AccountShare(userToShareWith));

    }
}
