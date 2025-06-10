package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductCost;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CostRepo extends BaseRepo<ProductCost, Integer> {

    @Query("from ProductCost pt where pt.id = ?1 and pt.deleted = false")
    Optional<ProductCost> findDataById(int id);

    @Query("from ProductCost pt where pt.product.id = ?1 and pt.deleted = false order by pt.id desc ")
    List<ProductCost> findByProductId(int pId);

    @Query("from ProductCost pt where pt.product.id = ?1 and pt.deleted = false and pt.defaultCost = true")
    Optional<ProductCost> findDefault(int pId);

    @Modifying
    @Transactional
    @Query("update ProductCost pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
