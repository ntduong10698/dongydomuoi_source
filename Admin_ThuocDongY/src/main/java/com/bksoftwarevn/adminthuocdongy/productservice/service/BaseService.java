package com.bksoftwarevn.adminthuocdongy.productservice.service;

import java.util.Optional;

public interface BaseService<T> {

    Optional<T> findById(int id) throws Exception;

    Optional<T> save(T data) throws Exception;

    boolean delete(int id) throws Exception;
}
