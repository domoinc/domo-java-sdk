package com.domo.sdk.users;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.users.model.User;
import org.junit.Test;

import java.io.IOException;

public class UpdateExample extends ExampleBase {

  @Test
  public void userClient_smokeTest() throws IOException {
    UserClient userClient = client.userClient();

    //Create a user
    User user = CreateExample.create(userClient);

    //Update a User
    user.setName("Leo Euler");
    user = userClient.update(user.getId(), user);
  }
}
