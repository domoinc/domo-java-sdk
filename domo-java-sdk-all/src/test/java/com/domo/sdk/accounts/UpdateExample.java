package com.domo.sdk.accounts;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.accounts.model.Account;
import org.junit.Test;

import java.io.IOException;

public class UpdateExample extends ExampleBase {

    @Test
    public void update_account_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        Account account = createAccount(accountClient);

        account.setName("Bob Smith");

        accountClient.update(account.getId(), account);

        System.out.println("Updated account: " + account);
    }

    public static Account createAccount(AccountClient accountClient) {

        Account account = new Account();
        account.setName("Earl Scruggs");

        return accountClient.create(account);
    }

}
