package com.bksoftwarevn.adminthuocdongy.newsservice.repository;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface NCategoryRepo extends BaseRepo<NCategory, Integer>{

    @Query("from NCategory x where x.id = ?1 and x.deleted = false")
    Optional<NCategory> findDataById(int id);


    @Query("from NCategory x where x.companyId = ?1 and x.deleted = false order by x.id desc ")
    List<NCategory> findByCompany(int comId);

    @Modifying
    @Transactional
    @Query("update NCategory x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
