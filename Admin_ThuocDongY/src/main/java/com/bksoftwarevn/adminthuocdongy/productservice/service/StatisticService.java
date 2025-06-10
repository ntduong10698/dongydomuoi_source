package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductStatistic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StatisticService {

    List<ProductStatistic> findByProduct(int productId, Date from, Date to);

    Optional<ProductStatistic> findTotal(int productId);

    void increaseViewed(int productId);

    void increaseSold(int productId, int amount);

    void createStatistic(int productId);
}
