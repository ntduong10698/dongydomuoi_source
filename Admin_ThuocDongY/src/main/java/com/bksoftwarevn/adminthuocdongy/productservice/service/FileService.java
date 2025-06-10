package com.bksoftwarevn.adminthuocdongy.productservice.service;


import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductFile;

import java.util.List;

public interface FileService extends BaseService<ProductFile>{

    void saveAll(List<ProductFile> files);

    List<ProductFile> findByProduct(int id, int type);
}
