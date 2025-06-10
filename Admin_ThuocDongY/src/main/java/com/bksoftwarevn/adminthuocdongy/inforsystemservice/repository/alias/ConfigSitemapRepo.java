package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.ConfigSitemap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ConfigSitemapRepo extends JpaRepository<ConfigSitemap, Integer> {

    ConfigSitemap findByCompanyIdAndDeleted(int id, boolean deleted);

    @Query("select c from ConfigSitemap c where c.deleted = ?1")
    List<ConfigSitemap> findAll(boolean deleted);

    List<ConfigSitemap> findByUrlDirectoryAndDeleted(String url, boolean deleted);

    @Modifying
    @Transactional
    @Query("update ConfigSitemap c set c.deleted = true where c.companyId = ?1")
    int deleteByCompanyId(int id);

    @Modifying
    @Transactional
    @Query("update ConfigSitemap c set c.status = ?2 where c.companyId = ?1")
    int changeStatus(int companyid, int status);

    @Query("select c from ConfigSitemap c where " +
            "((?4 is null) or (c.deleted = ?4)) and " +
            "((?3 = -1) or (c.status = ?3)) and " +
            "((?2 = 0) or (c.companyId = ?2)) and " +
            "c.urlDirectory like concat('%',?1,'%')")
    List<ConfigSitemap> filter(String urlDirectory, int companyId, int status, Boolean deleted);

}
