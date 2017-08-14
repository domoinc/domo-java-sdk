package com.domo.sdk.users;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.users.model.User;
import org.junit.Test;

import java.io.IOException;

public class RetrieveExample extends ExampleBase {

  @Test
  public void userClient_smokeTest() throws IOException {
    UserClient userClient = client.userClient();

    //Create a user
    User user = CreateExample.create(userClient);

    //Get a user
    user = userClient.get(user.getId());

  }
}
