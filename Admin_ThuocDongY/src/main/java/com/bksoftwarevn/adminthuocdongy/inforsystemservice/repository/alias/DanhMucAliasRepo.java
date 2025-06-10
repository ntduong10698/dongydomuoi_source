package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.DanhMucAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DanhMucAliasRepo extends JpaRepository<DanhMucAlias, Integer> {

    @Query("select d from DanhMucAlias d where d.id = ?1 and d.deleted = ?2")
    DanhMucAlias findById(int id, boolean deleted);

    @Query("select d from DanhMucAlias d where d.deleted = ?1")
    List<DanhMucAlias> findAll(boolean deleted);

    @Modifying
    @Transactional
    @Query("update DanhMucAlias d set d.deleted = true where d.id = ?1")
    int delete(int id);

    @Query("select d from DanhMucAlias d where d.configSitemap.companyId = ?1 and d.deleted = false")
    List<DanhMucAlias> findByCompanyId(int id);

}
