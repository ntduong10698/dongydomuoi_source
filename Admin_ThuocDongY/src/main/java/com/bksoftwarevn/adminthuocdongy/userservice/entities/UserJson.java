package com.bksoftwarevn.adminthuocdongy.userservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserJson {

    private String username;
    private int id;
    private int comId;
    private boolean admin;
    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();
}
