package com.domo.sdk.accounts;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.accounts.model.Account;
import com.domo.sdk.accounts.model.AccountType;
import org.junit.Test;

import java.io.IOException;

public class RetrieveExample extends ExampleBase {

    @Test
    public void retrieve_account_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        Account account = CreateExample.createAccount(accountClient);

        Account retrieved = accountClient.get(account.getId());

        System.out.println("Retrieved account: " + retrieved);
    }

    @Test
    public void retrieve_account_type_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        AccountType accountType = accountClient.getAccountType("facebook");

        System.out.println("Retrieved account type = " + accountType);
    }
}
