package com.domo.sdk.groups;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.groups.model.Group;
import com.domo.sdk.groups.model.UpdateGroupRequest;
import org.junit.Test;

public class UpdateExample extends ExampleBase {
  @Test
  public void groupClient_smokeTest() {
    GroupClient gClient = client.groupClient();

    //Create group
    Group group = CreateExample.createGroup(gClient);

    //Update group
    UpdateGroupRequest ugroup = new UpdateGroupRequest();
    ugroup.setName("DeleteMe"+System.currentTimeMillis());
    ugroup.setActive(true);
    Group g4 = gClient.update(group.getId(), ugroup);
    System.out.println("Update:"+g4);

  }

}
