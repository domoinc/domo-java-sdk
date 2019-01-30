package com.domo.sdk.accounts;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.accounts.model.Account;
import com.domo.sdk.accounts.model.AccountType;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListExample extends ExampleBase {

    @Test
    public void list_accounts_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        String sortBy = "name";
        int limit = 5;
        int offset = 0;
        List<Account> list = accountClient.list(sortBy, limit, offset);
        System.out.println("Account list: " + list);
    }

    @Test
    public void list_account_types_example() throws IOException {
        AccountClient accountClient = client.accountClient();

        String sortBy = "name";
        int limit = 5;
        int offset = 0;
        List<AccountType> list = accountClient.listAccountTypes(sortBy, limit, offset);
        System.out.println("Account Types: " + list);
    }

}
