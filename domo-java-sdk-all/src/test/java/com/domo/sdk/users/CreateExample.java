package com.domo.sdk.users;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.users.model.CreateUserRequest;
import com.domo.sdk.users.model.User;
import org.junit.Test;

import java.io.IOException;

public class CreateExample extends ExampleBase {

  @Test
  public void userClient_smokeTest() throws IOException {
    UserClient userClient = client.userClient();

    create(userClient);
  }

  public static User create(UserClient userClient) {
    // Build a User request
    CreateUserRequest request = new CreateUserRequest();
    request.setName("Leonhard Euler");
    request.setEmail("leonhard.euler@domo.com");
    request.setRole("Privileged");
    boolean sendInvite = true;

    //Create a user
    return userClient.create(sendInvite, request);
  }
}
