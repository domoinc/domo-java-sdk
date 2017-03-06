package com.domo.sdk.users;


import java.util.Objects;

public class CreateUserRequest {
    private String email;
    private String role;
    private String name;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String email, String role, String name) {
        this.email = email;
        this.role = role;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest user = (CreateUserRequest) o;
        return  Objects.equals(email, user.email) &&
                Objects.equals(role, user.role) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role, name);
    }
}
