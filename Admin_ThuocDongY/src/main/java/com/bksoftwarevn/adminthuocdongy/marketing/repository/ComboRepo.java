package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Combo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ComboRepo extends BaseRepo<Combo, Integer>{

    @Query("from Combo c where c.companyId = ?1 and c.deleted = false ")
    Page<Combo> findByCompany(int comId, Pageable pageable);

    @Query("from Combo x where x.id = ?1 and x.deleted = false")
    Optional<Combo> findDataById(int id);

    @Modifying
    @Transactional
    @Query("update Combo x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
