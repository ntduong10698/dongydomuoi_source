package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.HistoryChangeSitemap;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias.HistoryChangeSitemapRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.HistoryChangeSitemapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoryChangeSitemapServiceImpl implements HistoryChangeSitemapService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigSitemapServiceImpl.class);

    @Autowired
    private HistoryChangeSitemapRepo historyChangeSitemapRepo;

    @Override
    public Page<HistoryChangeSitemap> findByCompanyId(int companyId, Pageable pageable) {
        try{
            return historyChangeSitemapRepo.findByCompanyId(companyId, pageable);
        }catch (Exception ex) {
            logger.error("find by companyId err {0}", ex);
            throw ex;
        }
    }

    @Override
    public HistoryChangeSitemap save(HistoryChangeSitemap historyChangeSitemap) {
        try {
            return historyChangeSitemapRepo.save(historyChangeSitemap);
        } catch (Exception ex) {
            logger.error("save err {0}", ex);
            throw ex;
        }
    }
}
