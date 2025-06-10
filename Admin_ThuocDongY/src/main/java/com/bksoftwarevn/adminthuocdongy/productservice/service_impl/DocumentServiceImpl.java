package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Document;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.DocumentRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DocumentServiceImpl extends BaseServiceImpl<Document, Integer, DocumentRepo> implements DocumentService {

    public DocumentServiceImpl(DocumentRepo repo) {
        super(repo, "Document");
    }

    @Override
    public List<Document> findByCompany(int id) {
        return repo.findByCompanyId(id);
    }

}
