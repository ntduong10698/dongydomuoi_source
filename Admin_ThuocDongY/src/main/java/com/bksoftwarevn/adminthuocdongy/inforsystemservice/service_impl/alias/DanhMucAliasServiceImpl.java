package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.DanhMucAlias;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias.DanhMucAliasRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.DanhMucAliasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucAliasServiceImpl implements DanhMucAliasService {

    private static final Logger logger = LoggerFactory.getLogger(DanhMucAliasServiceImpl.class);

    @Autowired
    private DanhMucAliasRepo danhMucAliasRepo;

    @Override
    public DanhMucAlias findById(int id) {
        try{
            return danhMucAliasRepo.findById(id, false);
        }catch (Exception ex) {
            logger.error("find by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<DanhMucAlias> findAll() {
        try{
            return danhMucAliasRepo.findAll(false);
        }catch (Exception ex) {
            logger.error("find all err {0}", ex);
            throw ex;
        }
    }

    @Override
    public int delete(int id) {
        try{
            return danhMucAliasRepo.delete(id);
        }catch (Exception ex) {
            logger.error("delete err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<DanhMucAlias> findByCompanyId(int companyId) {
        try{
            return danhMucAliasRepo.findByCompanyId(companyId);
        }catch (Exception ex) {
            logger.error("find by company id err {0}", ex);
            throw ex;
        }
    }
}
