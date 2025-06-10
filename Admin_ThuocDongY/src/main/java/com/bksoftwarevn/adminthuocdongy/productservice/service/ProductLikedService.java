package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;

import java.util.List;

public interface ProductLikedService {

    List<Product> findByUser(int userId);

    void save(int userId, List<Product> products);

    void delete(int userId, List<Integer> productIds);
}
