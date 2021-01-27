package com.domo.sdk.accounts.hal;

import com.google.gson.annotations.SerializedName;

public final class Templates {

    @SerializedName("default")
    private Template defaultTemplate;

    public Template getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(final Template defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }
}
