package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.UrlAlias;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias.UrlAliasRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.UrlAliasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlAliasServiceImpl implements UrlAliasService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigSitemapServiceImpl.class);

    @Autowired
    private UrlAliasRepo urlAliasRepo;

    @Override
    public List<UrlAlias> findByCompanyIdAndAlias(int id, String alias) {
        try {
            return urlAliasRepo.findByCompanyIdAndDeletedAndAlias(id, false, alias);
        } catch (Exception ex) {
            logger.error("find by companyId err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean deletedById(int id) {
        try {
            return urlAliasRepo.deleteById(id) > 0;
        } catch (Exception ex) {
            logger.error("deleted err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean deleteByAlias(String alias, int comId) {
        try {
            UrlAlias alias1 = urlAliasRepo.findByAliasAndCom(alias, comId);
            return urlAliasRepo.deleteById(alias1.getId()) > 0;
        } catch (Exception ex) {
            logger.error("deleted err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean checkAlias(String alias, int comId) {
        try {
            return urlAliasRepo.checkAlias(alias, comId) > 0;
        } catch (Exception ex) {
            logger.error("check err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<UrlAlias> filter(String alias, String url, int companyId, Pageable pageable) {
        try {
            return urlAliasRepo.filter(alias, url, companyId, false, pageable);
        } catch (Exception ex) {
            logger.error("filter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public UrlAlias save(UrlAlias urlAlias) {
        try {
            return urlAliasRepo.save(urlAlias);
        } catch (Exception ex) {
            logger.error("save err {0}", ex);
            throw ex;
        }
    }

    @Override
    public UrlAlias findById(int id) {
        try {
            return urlAliasRepo.findByIdAndDeleted(id, false);
        } catch (Exception ex) {
            logger.error("find by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<UrlAlias> findByDanhMucAlias(int id) {
        try {
            return urlAliasRepo.findByDanhMucAlias(id);
        } catch (Exception ex) {
            logger.error("find by id err {0}", ex);
            throw ex;
        }
    }
}
