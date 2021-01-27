package com.domo.sdk.accounts;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.accounts.model.Account;
import org.junit.Test;

import java.io.IOException;

public class CreateExample extends ExampleBase {

    @Test
    public void create_account_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        Account account = createAccount(accountClient);

        System.out.println("Created account: " + account);
    }

    public static Account createAccount(AccountClient accountClient) {

        Account account = new Account();
        account.setName("Earl Scruggs");

        return accountClient.create(account);
    }

}
