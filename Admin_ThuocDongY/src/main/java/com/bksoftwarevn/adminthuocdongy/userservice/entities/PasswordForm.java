package com.bksoftwarevn.adminthuocdongy.userservice.entities;

import lombok.Data;

@Data
public class PasswordForm {
    private String oldPass;
    private String newPass;
}
