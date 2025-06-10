package com.bksoftwarevn.adminthuocdongy.newsservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NCategory;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NCategoryRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NCategoryServiceImpl extends BaseServiceImpl<NCategory, Integer, NCategoryRepo> implements NCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(NCategoryServiceImpl.class);

    public NCategoryServiceImpl(NCategoryRepo repo) {
        super(repo, "Category");
    }

    @Override
    public List<NCategory> findByCompany(int comId) {
        try {
            return repo.findByCompany(comId);
        }catch (Exception ex){
            logger.error("find category by com err {0}", ex);
            throw ex;
        }
    }
}
