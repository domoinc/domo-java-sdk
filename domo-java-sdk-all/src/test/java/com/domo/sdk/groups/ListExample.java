package com.domo.sdk.groups;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.groups.model.Group;
import org.junit.Test;

import java.util.List;

public class ListExample extends ExampleBase {
  @Test
  public void groupClient_smokeTest() {
    GroupClient gClient = client.groupClient();

    List<Group> list = gClient.list(10, 0);
    System.out.println("list:" + list);
  }

}
