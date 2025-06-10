package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService extends BaseService<Brand> {
    Page<Brand> findByCompany(int comId, Pageable pageable) throws Exception;
}
