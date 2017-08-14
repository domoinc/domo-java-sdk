package com.domo.sdk.groups;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.groups.model.Group;
import org.junit.Test;

public class DeleteExample extends ExampleBase {
  @Test
  public void groupClient_smokeTest() {
    GroupClient gClient = client.groupClient();

    //Create group
    Group group = CreateExample.createGroup(gClient);

    //Delete group
    gClient.delete(group.getId());
  }

}
