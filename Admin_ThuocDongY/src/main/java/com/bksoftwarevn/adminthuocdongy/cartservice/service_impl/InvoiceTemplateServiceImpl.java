package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.InvoiceTemplate;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.InvoiceFormRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.InvoiceTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class InvoiceTemplateServiceImpl extends BaseServiceImpl<InvoiceTemplate,Integer, InvoiceFormRepo> implements InvoiceTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceTemplateServiceImpl.class);

    public InvoiceTemplateServiceImpl(InvoiceFormRepo repo) {
        super(repo, "invoice_form");
    }

    @Override
    public Page<InvoiceTemplate> findAll(Pageable pageable, boolean ascName) {
        try {
            if(ascName){
                return repo.findAllAsc(pageable);
            }else{
                return repo.findAllDsc(pageable);
            }
        }catch (Exception ex){
            logger.error("find all InvoiceForm by id err {0}", ex);
            return null;
        }
    }

    @Override
    public Page<InvoiceTemplate> filter(String text, Pageable pageable, boolean ascName) {
        try{
            if(ascName){
                return repo.filterAsc(text,pageable);
            }else{
                return repo.filterDesc(text,pageable);
            }
        }catch (Exception ex){
            logger.error("find status filter err {0}", ex);
            throw ex;
        }
    }
}
