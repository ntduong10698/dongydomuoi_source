package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductProperty;

import java.util.List;
import java.util.Optional;

public interface PropertyService extends BaseService<ProductProperty>{

    Optional<List<ProductProperty>> findByProductType(int typeId) throws Exception;
}
