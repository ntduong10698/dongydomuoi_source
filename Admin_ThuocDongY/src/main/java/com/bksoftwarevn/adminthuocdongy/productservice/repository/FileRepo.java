package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductFile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepo extends BaseRepo<ProductFile, Integer> {
    
    @Query("from ProductFile pt where pt.id = ?1 and pt.deleted = false")
    Optional<ProductFile> findDataById(int id);

    @Query("from ProductFile pt where pt.product.id = ?1 and ( ?2 = 0 or pt.type = ?2 ) and pt.deleted = false order by pt.stt asc ")
    List<ProductFile> findByProductId(int proId, int type);

    @Modifying
    @Transactional
    @Query("update ProductFile pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
    
}
