package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BrandRepo extends BaseRepo<Brand, Integer> {

    @Query("from Brand pt where pt.id = ?1 and pt.deleted = false")
    Optional<Brand> findDataById(int id);

    @Query("from Brand pt where pt.companyId = ?1 and pt.deleted = false order by pt.id desc ")
    Page<Brand> findByCompanyId(int comId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Brand pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
