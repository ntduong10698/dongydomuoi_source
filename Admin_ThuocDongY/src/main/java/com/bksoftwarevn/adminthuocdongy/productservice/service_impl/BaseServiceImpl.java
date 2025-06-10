package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.repository.BaseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class BaseServiceImpl<Data,Key, Repo extends BaseRepo<Data, Key>>{

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    protected Repo repo;

    protected String objectName;

    public BaseServiceImpl(Repo repo, String objectName) {
        this.repo = repo;
        this.objectName = objectName;
    }

    public Optional<Data> findById(int id) throws Exception {
        try {
            return repo.findDataById(id);
        } catch (Exception ex) {
            logger.error("find " + objectName + " by id err {}", ex);
            throw ex;
        }
    }

    public Optional<Data> save(Data data) throws Exception {
        try {
            return Optional.of(repo.save(data));
        } catch (Exception ex) {
            logger.error("save " + objectName + " err {}", ex);
            throw ex;
        }
    }

    public boolean delete(int id) throws Exception {
        try {
            return repo.deleteDataById(id) > 0;
        } catch (Exception ex) {
            logger.error("delete " + objectName + " err {}", ex);
            throw ex;
        }
    }

}
