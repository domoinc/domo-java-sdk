package com.domo.sdk.accounts.hal;

import java.util.List;

public final class Template {

    private String title, contentType, method, key;

    private List<Property> properties;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(final List<Property> properties) {
        this.properties = properties;
    }
}
