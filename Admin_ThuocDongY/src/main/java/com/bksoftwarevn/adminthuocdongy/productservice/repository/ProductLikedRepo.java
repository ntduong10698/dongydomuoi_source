package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductLiked;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.ProductLikeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductLikedRepo extends JpaRepository<ProductLiked, ProductLikeKey> {

    @Query("select pl.product from ProductLiked pl where pl.id.userId = ?1 and pl.deleted = false")
    List<Product> findByUser(int userId);

    @Modifying
    @Transactional
    @Query("update ProductLiked pl set pl.deleted = true where pl.id = ?1")
    int delete(ProductLikeKey id);

}
