package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends BaseRepo<Product, Integer> {

    @Query("from Product pt where pt.id = ?1 and pt.deleted = false")
    Optional<Product> findDataById(int id);

    @Query("from Product pt where pt.id in (?1) and pt.deleted = false")
    List<Product> findDataByIds(List<Integer> ids);

    @Query("select count (x) from Product x where x.brand.id = ?1 and x.deleted = false")
    int countByBrand(int brandId);

    @Modifying
    @Transactional
    @Query("update Product pt set pt.deleted = true where pt.id = ?1")
    int deleteDataById(int id);

    @Modifying
    @Transactional
    @Query("update Product pt set pt.deleted = true where pt.id in (?1)")
    int deleteDataById(List<Integer> ids);

    @Modifying
    @Transactional
    @Query("update Product pt set pt.status = ?1 where pt.id in (?2)")
    int alterStatusById(int status ,List<Integer> ids);

    @Modifying
    @Transactional
    @Query("update Product pt set pt.quantity = pt.quantity - ?1 where pt.id in (?2)")
    int decreaseById(int number , int id);

}
