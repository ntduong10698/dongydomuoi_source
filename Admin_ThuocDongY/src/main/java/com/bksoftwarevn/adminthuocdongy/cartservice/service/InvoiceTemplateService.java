package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.InvoiceTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceTemplateService extends BaseService<InvoiceTemplate> {

    Page<InvoiceTemplate> findAll(Pageable pageable, boolean ascName);

    Page<InvoiceTemplate> filter(String text, Pageable pageable, boolean ascName);

}
