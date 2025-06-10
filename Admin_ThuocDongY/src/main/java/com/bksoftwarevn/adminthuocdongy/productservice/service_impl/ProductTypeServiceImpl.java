package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductType;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.ProductTypeRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType,Integer, ProductTypeRepo> implements ProductTypeService  {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

    public ProductTypeServiceImpl(ProductTypeRepo repo) {
        super(repo, "ProductType");
    }

    @Override
    public Optional<List<ProductType>> findByCompany(Boolean enableView,int comId) throws Exception{
        try {
            return Optional.ofNullable(repo.findByCompanyId(enableView, comId));
        }catch (Exception ex){
            logger.error("find productType by company err {0}", ex);
            throw ex;
        }
    }
}
