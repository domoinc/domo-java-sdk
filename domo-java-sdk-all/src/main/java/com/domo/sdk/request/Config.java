package com.domo.sdk.request;

public class Config {
    private final String clientId;
    private final String secret;
    private String apiHost;
    private boolean useHttps = true;

    public Config(String clientId, String secret, String apiHost, boolean useHttps) {
        this.clientId = clientId;
        this.secret = secret;
        this.apiHost = apiHost;
        this.useHttps = useHttps;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }

    public String getApiHost() {
        return apiHost;
    }

    public boolean useHttps() {
        return useHttps;
    }
}
