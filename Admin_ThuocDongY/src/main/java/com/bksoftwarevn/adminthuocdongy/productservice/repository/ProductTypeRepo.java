package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepo extends BaseRepo<ProductType, Integer> {

    @Query("from ProductType pt where pt.id = ?1 and pt.deleted = false")
    Optional<ProductType> findDataById(int id);

    @Query("from ProductType pt where pt.companyId = ?2 and (?1 is null or pt.enableView = ?1) and pt.deleted = false order by pt.stt ")
    List<ProductType> findByCompanyId(Boolean enableView, int comId);

    @Modifying
    @Transactional
    @Query("update ProductType pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
