package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService extends BaseService<ProductType> {

    Optional<List<ProductType>> findByCompany(Boolean enableView, int comId) throws Exception;
}
