package com.domo.sdk.accounts.model;

public final class Account {

    private String id, name;
    private Boolean valid;
    private AccountType type;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(final Boolean valid) {
        this.valid = valid;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(final AccountType type) {
        this.type = type;
    }
}
