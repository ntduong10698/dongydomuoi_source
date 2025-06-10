package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.TanSuatThayDoiAlias;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias.TanSuatThayDoiAliasRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.TanSuatThayDoiAliasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TanSuatThayDoiAliasServiceImpl implements TanSuatThayDoiAliasService {

    private static final Logger logger = LoggerFactory.getLogger(TanSuatThayDoiAliasServiceImpl.class);

    @Autowired
    private TanSuatThayDoiAliasRepo tanSuatThayDoiAliasRepo;

    @Override
    public TanSuatThayDoiAlias findById(int id) {
        try{
            return tanSuatThayDoiAliasRepo.findById(id, false);
        }catch (Exception ex) {
            logger.error("find by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<TanSuatThayDoiAlias> findAll() {
        try{
            return tanSuatThayDoiAliasRepo.findAll(false);
        }catch (Exception ex) {
            logger.error("find all {0}", ex);
            throw ex;
        }
    }

    @Override
    public int delete(int id) {
        try{
            return tanSuatThayDoiAliasRepo.delete(id);
        }catch (Exception ex) {
            logger.error("delete err {0}", ex);
            throw ex;
        }
    }
}
