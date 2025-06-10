package com.bksoftwarevn.adminthuocdongy.userservice.entities;

import lombok.Data;

@Data
public class JWTEntity {
    private String code;
    private long duration;
}
