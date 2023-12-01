package com.domo.sdk.request;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransportTest {

    @Test
    public void methodJson_success() throws IOException {
        // Prep
        ResponseBody respBody = mock(ResponseBody.class);
        String respBodyAsString = "{\"note\": \"my note\"}";
        when(respBody.string()).thenReturn(respBodyAsString);
        when(respBody.charStream()).thenReturn(new StringReader(respBodyAsString));
        Response response = mock(Response.class);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(respBody);
        Call call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        OkHttpClient httpClient = mock(OkHttpClient.class);
        when(httpClient.newCall(any())).thenReturn(call);
        HttpUrl url = mock(HttpUrl.class);
        String requestBody = "{\"id\": \"1\"}";
        Transport unit = new Transport(httpClient);
        // Act / Check
        //noinspection unchecked
        Map<String, String> result = unit.methodJson("POST", url, requestBody, Map.class);
        // Check
        assertThat(result).containsEntry("note", "my note");
    }

    @Test
    public void methodJson_requestException_when_response_body_json() throws IOException {
        // Prep
        ResponseBody respBody = mock(ResponseBody.class);
        String respBodyAsString = "{\"note\": \"my note\"}";
        when(respBody.string()).thenReturn(respBodyAsString);
        when(respBody.charStream()).thenReturn(new StringReader(respBodyAsString));
        Response response = mock(Response.class);
        when(response.isSuccessful()).thenReturn(false);
        when(response.body()).thenReturn(respBody);
        Call call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        OkHttpClient httpClient = mock(OkHttpClient.class);
        when(httpClient.newCall(any())).thenReturn(call);
        HttpUrl url = mock(HttpUrl.class);
        String requestBody = "{\"id\": \"1\"}";
        Transport unit = new Transport(httpClient);
        // Act / Check
        assertThatThrownBy(() -> unit.methodJson("POST", url, requestBody, Map.class))
                .isInstanceOf(RequestException.class);
    }

    @Test
    public void methodJson_requestException_when_response_body_empty() throws IOException {
        // Prep
        ResponseBody respBody = mock(ResponseBody.class);
        String respBodyAsString = "";
        when(respBody.string()).thenReturn(respBodyAsString);
        when(respBody.charStream()).thenReturn(new StringReader(respBodyAsString));
        Response response = mock(Response.class);
        when(response.isSuccessful()).thenReturn(false);
        when(response.body()).thenReturn(respBody);
        Call call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        OkHttpClient httpClient = mock(OkHttpClient.class);
        when(httpClient.newCall(any())).thenReturn(call);
        HttpUrl url = mock(HttpUrl.class);
        String requestBody = "{\"id\": \"1\"}";
        Transport unit = new Transport(httpClient);
        // Act / Check
        assertThatThrownBy(() -> unit.methodJson("POST", url, requestBody, Map.class))
                .isInstanceOf(RequestException.class);
    }

}
