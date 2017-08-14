package com.domo.sdk.groups;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.groups.model.Group;
import org.junit.Test;

public class CreateExample extends ExampleBase {
  @Test
  public void groupClient_smokeTest() {
    GroupClient gClient = client.groupClient();

    CreateExample.createGroup(gClient);
  }

  public static Group createGroup(GroupClient gClient) {
    Group testGroup = new Group();
    testGroup.setName("Test Group"+System.currentTimeMillis());

    return gClient.create(testGroup);
  }

}
