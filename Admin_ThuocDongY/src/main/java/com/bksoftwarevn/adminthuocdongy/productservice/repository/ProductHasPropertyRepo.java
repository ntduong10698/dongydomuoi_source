package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductHasProperty;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.ProductPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductHasPropertyRepo extends JpaRepository<ProductHasProperty, ProductPropertyKey> {

    @Query("from ProductHasProperty php where php.id.productId = ?1 and php.deleted = false")
    List<ProductHasProperty> findByProduct(int productId);

    @Modifying
    @Transactional
    @Query("update ProductHasProperty php set php.deleted = true where php.id.productId = ?1 and php.id.propertyId = ?2")
    int delele(int product, int propertyId);

}
