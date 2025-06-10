package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Status;

import java.util.List;

public interface StatusService extends BaseService<Status> {

    List<Status> findAll();

}
