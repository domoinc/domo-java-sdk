package com.domo.sdk.accounts.hal;

import java.net.URI;

public final class Property {

    private String name, prompt, value;
    private String regex;
    private Boolean readyOnly, required;
    private URI templated;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(final String regex) {
        this.regex = regex;
    }

    public Boolean isReadOnly() {
        return readyOnly;
    }

    public void setReadyOnly(final Boolean readyOnly) {
        this.readyOnly = readyOnly;
    }

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(final Boolean required) {
        this.required = required;
    }

    public URI getTemplated() {
        return templated;
    }

    public void setTemplated(final URI templated) {
        this.templated = templated;
    }
}
