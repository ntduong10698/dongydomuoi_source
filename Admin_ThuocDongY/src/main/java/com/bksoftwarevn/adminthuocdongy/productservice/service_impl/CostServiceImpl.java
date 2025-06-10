package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductCost;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.CostRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostServiceImpl extends BaseServiceImpl<ProductCost, Integer, CostRepo> implements CostService {

    private static final Logger logger = LoggerFactory.getLogger(CostServiceImpl.class);

    public CostServiceImpl(CostRepo repo) {
        super(repo, "ProductCost");
    }

    @Override
    public void saveAll(List<ProductCost> costs) {
        try {
           repo.saveAll(costs);
        }catch (Exception ex){
            logger.error("find costs by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ProductCost> findByProduct(int id) {
        try {
            return repo.findByProductId(id);
        }catch (Exception ex){
            logger.error("find costs by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<ProductCost> findDefaultCost(int id) {
        try {
            return repo.findDefault(id);
        }catch (Exception ex){
            logger.error("find default cost by product err {0}", ex);
            throw ex;
        }
    }
}
