package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends BaseRepo<Category, Integer> {

    @Query("from Category x where x.id = ?1 and x.deleted = false")
    Optional<Category> findDataById(int id);

    @Query("from Category x where x.parentId is null and x.productType.id = ?1 and x.deleted = false order by x.stt")
    List<Category> findByProductType(int typeId);

    @Query("from Category x where x.parentId = ?1 and x.deleted = false order by x.stt")
    List<Category> findByParent(int parentId);

    @Modifying
    @Transactional
    @Query("update Category pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);
}
