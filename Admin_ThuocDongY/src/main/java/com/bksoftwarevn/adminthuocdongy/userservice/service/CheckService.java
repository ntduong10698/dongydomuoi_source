package com.bksoftwarevn.adminthuocdongy.userservice.service;

import com.bksoftwarevn.adminthuocdongy.entities.UserJson;

import javax.servlet.http.HttpServletRequest;

public interface CheckService {
    UserJson check(String token);
    UserJson check(String token, HttpServletRequest request);
}
