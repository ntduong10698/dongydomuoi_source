package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductProperty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepo extends BaseRepo<ProductProperty, Integer>{

    @Query("from ProductProperty x where x.id = ?1 and x.deleted = false")
    Optional<ProductProperty> findDataById(int id);

    @Query("from ProductProperty x where x.productType.id = ?1 and x.deleted = false ")
    List<ProductProperty> findByProductType(int cateId);

    @Modifying
    @Transactional
    @Query("update ProductProperty pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
