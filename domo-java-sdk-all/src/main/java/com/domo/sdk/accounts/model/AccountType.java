package com.domo.sdk.accounts.model;

import com.domo.sdk.accounts.hal.Templates;

import java.util.Map;

public final class AccountType {

    private String id;
    private String name;
    private Templates _templates;
    private Map<String, String> properties;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Templates get_templates() {
        return _templates;
    }

    public void set_templates(final Templates _templates) {
        this._templates = _templates;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }

}
