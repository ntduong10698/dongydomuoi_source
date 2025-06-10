package com.bksoftwarevn.adminthuocdongy.userservice.entities;

public class RegisterForm {

    private String email;

    private String name;

    private int companyId;

    private String password;

    private String username;

    public String getEmail() {
        return email;
    }

    public RegisterForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterForm setName(String name) {
        this.name = name;
        return this;
    }

    public int getCompanyId() {
        return companyId;
    }

    public RegisterForm setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterForm setUsername(String username) {
        this.username = username;
        return this;
    }
}
