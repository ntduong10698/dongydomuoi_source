package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Document;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepo extends BaseRepo<Document, Integer>{
    
    @Query("from Document pt where pt.id = ?1 and pt.deleted = false")
    Optional<Document> findDataById(int id);

    @Query("from Document pt where pt.companyId = ?1 and pt.deleted = false order by pt.id desc ")
    List<Document> findByCompanyId( int comId);

    @Modifying
    @Transactional
    @Query("update Document pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
