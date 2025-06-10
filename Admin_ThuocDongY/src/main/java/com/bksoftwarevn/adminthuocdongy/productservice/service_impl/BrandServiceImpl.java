package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Brand;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.BrandRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends BaseServiceImpl<Brand, Integer, BrandRepo> implements BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    public BrandServiceImpl(BrandRepo repo) {
        super(repo, "brand");
    }

    @Override
    public Page<Brand> findByCompany(int comId, Pageable pageable) throws Exception {
        try {
            return repo.findByCompanyId(comId, pageable);
        }catch (Exception ex){
            logger.error("find brand by com err {0}", ex);
            throw ex;
        }
    }
}
