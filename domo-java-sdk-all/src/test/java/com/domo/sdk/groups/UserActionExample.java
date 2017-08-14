package com.domo.sdk.groups;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.groups.model.Group;
import com.domo.sdk.users.model.CreateUserRequest;
import com.domo.sdk.users.model.User;
import org.junit.Test;

public class UserActionExample extends ExampleBase {
  @Test
  public void groupClient_smokeTest() {
    GroupClient gClient = client.groupClient();

    //Create group
    Group group = CreateExample.createGroup(gClient);

    User user = client.userClient().create(false,
            new CreateUserRequest("deleteme.user" + System.currentTimeMillis() + "@domo.com", "Participant", "Delete Me - Smoke User"));


    System.out.println("Add user to group");
    gClient.addUserToGroup(group.getId(), user.getId());


    System.out.println("List users in group");
    gClient.listUsersInGroup(group.getId());


    System.out.println("Remove User");
    gClient.removeUserFromGroup(group.getId(), user.getId());


    System.out.println("List Users after removing them");
    gClient.listUsersInGroup(group.getId());

    client.userClient().delete(user.getId());
  }

}
