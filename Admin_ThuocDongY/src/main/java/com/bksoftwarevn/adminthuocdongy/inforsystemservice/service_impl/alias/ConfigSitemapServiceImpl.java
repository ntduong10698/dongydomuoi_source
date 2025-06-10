package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.ConfigSitemap;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias.ConfigSitemapRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.ConfigSitemapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigSitemapServiceImpl implements ConfigSitemapService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigSitemapServiceImpl.class);

    @Autowired
    private ConfigSitemapRepo configSitemapRepo;


    @Override
    public ConfigSitemap findByCompanyId(int id) {
        try{
            return configSitemapRepo.findByCompanyIdAndDeleted(id, false);
        }catch (Exception ex) {
            logger.error("find by companyId err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ConfigSitemap> findAll() {
        try{
            return configSitemapRepo.findAll(false);
        }catch (Exception ex) {
            logger.error("find all err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean deleted(int id) {
        try{
            return configSitemapRepo.deleteByCompanyId(id) > 0;
        }catch (Exception ex) {
            logger.error("deleted err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ConfigSitemap> filter(String urlDirectory, int companyId, int status) {
        try{
            return configSitemapRepo.filter(urlDirectory, companyId, status, false);
        }catch (Exception ex) {
            logger.error("filter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public ConfigSitemap save(ConfigSitemap configSitemap) {
        try {
            return configSitemapRepo.save(configSitemap);
        } catch (Exception ex) {
            logger.error("save err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean changeStatus(int companyId, int status) {
        try {
            return configSitemapRepo.changeStatus(companyId, status) > 0;
        } catch (Exception ex) {
            logger.error("save err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ConfigSitemap> findByUrlDirectory(String url) {
        try {
            return configSitemapRepo.findByUrlDirectoryAndDeleted(url, false);
        } catch (Exception ex) {
            logger.error("find by url directory err {0}", ex);
            throw ex;
        }
    }

}
