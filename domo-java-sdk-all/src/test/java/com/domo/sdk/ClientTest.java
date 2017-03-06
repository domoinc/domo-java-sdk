package com.domo.sdk;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {


    @Test
    public void client_shouldAllowOAuthToken_onCreation() throws IOException {
        Client client = Client.create("d726726c-f1f2-4f90-abf3-6c442a5e7340",
                "2cdcc6c2a4d94dd444ff3d648ff0254d8f1d442a1c43d38c1f96e405fb4691fd");


        assertThat(client.userClient().list(1, 0)).hasSize(1);
    }

}
