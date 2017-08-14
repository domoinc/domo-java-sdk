package com.domo.sdk.users;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.users.model.User;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListExample extends ExampleBase {

  @Test
  public void userClient_smokeTest() throws IOException {
    UserClient userClient = client.userClient();

    //List Users
    List<User> list = userClient.list(30, 0);
    System.out.println(list);
  }
}
