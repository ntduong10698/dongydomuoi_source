package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.HistoryChangeSitemap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryChangeSitemapRepo extends JpaRepository<HistoryChangeSitemap, Integer> {

    @Query("select h from HistoryChangeSitemap h where h.configSitemap.companyId = ?1")
    Page<HistoryChangeSitemap> findByCompanyId(int companyId, Pageable pageable);

}
