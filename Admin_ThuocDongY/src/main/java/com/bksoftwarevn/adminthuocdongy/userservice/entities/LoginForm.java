package com.bksoftwarevn.adminthuocdongy.userservice.entities;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;
    private int comId;
}
