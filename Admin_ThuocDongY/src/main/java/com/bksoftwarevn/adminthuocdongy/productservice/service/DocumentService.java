package com.bksoftwarevn.adminthuocdongy.productservice.service;


import com.bksoftwarevn.adminthuocdongy.productservice.entities.Document;

import java.util.List;

public interface DocumentService extends BaseService<Document> {

    List<Document> findByCompany(int id);

}
