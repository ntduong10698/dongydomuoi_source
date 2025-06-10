package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CommentRepo extends BaseRepo<ProductComment, Integer> {
    
    @Query("from ProductComment pt where pt.id = ?1 and pt.deleted = false")
    Optional<ProductComment> findDataById(int id);

    @Query("from ProductComment pt where pt.product.id = ?1 and (pt.accepted = ?2 or ?2 is null) and pt.deleted = false order by pt.id desc ")
    Page<ProductComment> findByProductId(int comId, Boolean accepted, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update ProductComment pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
