package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.SourceOrder;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.SourceOrderRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.SourceOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SourceOrderServiceImpl extends BaseServiceImpl<SourceOrder,Integer, SourceOrderRepo> implements SourceOrderService {

    private static final Logger logger = LoggerFactory.getLogger(SourceOrderServiceImpl.class);

    public SourceOrderServiceImpl(SourceOrderRepo repo) {
        super(repo, "source_order");
    }

    @Override
    public Page<SourceOrder> findAll(Pageable pageable, boolean ascName) {
        try{
            if(ascName){
                return repo.findAllAsc(pageable);
            }else{
                return repo.findAllDesc(pageable);
            }
        }catch (Exception ex) {
            logger.error("find all SourceOrder by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<SourceOrder> filter(String text, Pageable pageable, boolean ascName) {
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

    @Override
    public Optional<SourceOrder> findDefault(int comId) {
        try{
           return repo.findDefault(comId);
        }catch (Exception ex) {
            logger.error("find all SourceOrder by id err {0}", ex);
            throw ex;
        }
    }
}
