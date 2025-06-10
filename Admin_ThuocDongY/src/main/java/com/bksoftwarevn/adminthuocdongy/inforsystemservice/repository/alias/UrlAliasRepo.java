package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.UrlAlias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UrlAliasRepo extends JpaRepository<UrlAlias, Integer> {

    @Query("select u from UrlAlias u where u.deleted = ?2 and u.danhMucAlias.configSitemap.companyId = ?1 and u.alias = ?3")
    List<UrlAlias> findByCompanyIdAndDeletedAndAlias(int id, boolean deleted, String alias);

    @Modifying
    @Transactional
    @Query("update UrlAlias u set u.deleted = true where u.id = ?1")
    int deleteById(int id);

    @Modifying
    @Transactional
    @Query("update UrlAlias u set u.deleted = true where u.alias = ?1 and u.danhMucAlias.configSitemap.companyId = ?2")
    int deleteByAlias(String alias, int comId);

    @Query("select u from UrlAlias u where " +
            "((?4 is null) or (u.deleted = ?4)) and " +
            "((?3 = 0) or (u.danhMucAlias.configSitemap.companyId = ?3)) and " +
            "u.alias like concat('%',?1,'%') and " +
            "u.url like concat('%',?2,'%')" +
            "order by u.dateChange DESC")
    Page<UrlAlias> filter(String alias, String url, int companyId, Boolean deleted, Pageable pageable);

    @Query("select count(x) from UrlAlias x where x.alias = ?1 and x.danhMucAlias.configSitemap.companyId = ?2 and x.deleted = false ")
    int checkAlias(String alias, int comId);

    UrlAlias findByIdAndDeleted(int id, boolean deleted);

    @Query(" from UrlAlias x where x.alias = ?1 and x.danhMucAlias.configSitemap.companyId = ?2 and x.deleted = false")
    UrlAlias findByAliasAndCom(String alias, int comId);

    @Query("select u from UrlAlias u where u.danhMucAlias.id = ?1 and u.deleted = false")
    List<UrlAlias> findByDanhMucAlias(int id);
}
