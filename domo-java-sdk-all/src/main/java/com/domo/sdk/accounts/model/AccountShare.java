package com.domo.sdk.accounts.model;

import java.util.Objects;

public class AccountShare {

    private User user;

    public AccountShare(com.domo.sdk.users.model.User user) {
        this.user = new User(Objects.requireNonNull(user.getId()));
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    static class User {
        private final Long id;

        User(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }
}