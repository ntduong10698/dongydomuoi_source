package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductProperty;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.PropertyRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl extends BaseServiceImpl<ProductProperty, Integer, PropertyRepo> implements PropertyService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);


    public PropertyServiceImpl(PropertyRepo repo) {
        super(repo, "ProductProperty");
    }

    @Override
    public Optional<List<ProductProperty>> findByProductType(int typeId) throws Exception {
        try {
            return Optional.ofNullable(repo.findByProductType(typeId));
        }catch (Exception ex){
            logger.error("find property by productType err {0}", ex);
            throw ex;
        }
    }
}
