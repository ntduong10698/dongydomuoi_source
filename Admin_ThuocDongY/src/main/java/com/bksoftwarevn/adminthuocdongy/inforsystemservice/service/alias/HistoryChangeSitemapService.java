package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.HistoryChangeSitemap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryChangeSitemapService {

    Page<HistoryChangeSitemap> findByCompanyId(int companyId, Pageable pageable);

    HistoryChangeSitemap save(HistoryChangeSitemap historyChangeSitemap);
}
