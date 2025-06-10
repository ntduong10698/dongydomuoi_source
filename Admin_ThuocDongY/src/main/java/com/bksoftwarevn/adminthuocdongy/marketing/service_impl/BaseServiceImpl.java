package com.bksoftwarevn.adminthuocdongy.marketing.service_impl;

import com.bksoftwarevn.adminthuocdongy.marketing.repository.BaseRepo;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class BaseServiceImpl<Data,Key, Repo extends BaseRepo<Data, Key>> {

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
            log.error("find " + objectName + " by id err {}", ex);
            throw ex;
        }
    }

    public Optional<Data> save(Data data) throws Exception {
        try {
            return Optional.of(repo.save(data));
        } catch (Exception ex) {
            log.error("save " + objectName + " err {}", ex);
            throw ex;
        }
    }

    public boolean delete(int id) throws Exception {
        try {
            return repo.deleteDataById(id) > 0;
        } catch (Exception ex) {
            log.error("delete " + objectName + " err {}", ex);
            throw ex;
        }
    }

}
