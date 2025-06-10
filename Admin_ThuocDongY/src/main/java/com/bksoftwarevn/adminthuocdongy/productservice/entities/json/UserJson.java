package com.bksoftwarevn.adminthuocdongy.productservice.entities.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJson {

    private String username;
    private int id;
    private int comId;
    private boolean admin;
    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();
}
