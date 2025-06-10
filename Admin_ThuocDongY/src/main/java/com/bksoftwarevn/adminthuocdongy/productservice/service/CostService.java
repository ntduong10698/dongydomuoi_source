package com.bksoftwarevn.adminthuocdongy.productservice.service;


import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductCost;

import java.util.List;
import java.util.Optional;

public interface CostService extends BaseService<ProductCost> {

    void saveAll(List<ProductCost>  costs);

    List<ProductCost> findByProduct(int id);

    Optional<ProductCost> findDefaultCost(int id);
}
