package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.TanSuatThayDoiAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TanSuatThayDoiAliasRepo extends JpaRepository<TanSuatThayDoiAlias, Integer> {

    @Query("select t from TanSuatThayDoiAlias t where t.id = ?1 and t.deleted = ?2")
    TanSuatThayDoiAlias findById(int id, boolean deleted);

    @Query("select t from TanSuatThayDoiAlias t where t.deleted = ?1")
    List<TanSuatThayDoiAlias> findAll(boolean deleted);

    @Modifying
    @Transactional
    @Query("update TanSuatThayDoiAlias t set t.deleted = true where t.id = ?1")
    int delete(int id);
}
