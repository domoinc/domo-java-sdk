package com.domo.sdk.accounts;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.accounts.model.Account;
import org.junit.Test;

import java.io.IOException;

public class DeleteExample extends ExampleBase {

    @Test
    public void create_account_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        Account account = CreateExample.createAccount(accountClient);

        accountClient.delete(account.getId());

        System.out.println("Deleted account: " + account);
    }

}
