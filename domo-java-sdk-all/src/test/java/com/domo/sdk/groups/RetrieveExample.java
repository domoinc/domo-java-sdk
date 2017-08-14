package com.domo.sdk.groups;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.groups.model.Group;
import org.junit.Test;

public class RetrieveExample extends ExampleBase {
  @Test
  public void groupClient_smokeTest() {
    GroupClient gClient = client.groupClient();

    //Create group
    Group group = CreateExample.createGroup(gClient);

    //Retrieve
    Group g3 = gClient.get(group.getId());
    System.out.println("get:" + g3);
  }

}
