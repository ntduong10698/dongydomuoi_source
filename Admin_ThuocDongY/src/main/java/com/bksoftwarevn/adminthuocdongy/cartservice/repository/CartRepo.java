package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends BaseRepo<Cart,Integer>   {

    @Query("from Cart c where c.deleted = false and c.id = ?1")
    Optional<Cart> findDataById(int id);

    @Query("from Cart c where c.deleted = false and c.customerId = ?1 order by c.id desc , c.createdTime desc")
    List<Cart> findByCustomerId(int cusId);

    @Query(value = " select c from Cart c where c.deleted = false order by c.id , c.createdTime desc ")
    Page<Cart> findAll(Pageable pageable);

    @Query("select c from Cart c where c.deleted=false " +
            "and (?1 is null or c.createdTime >= ?1 ) and (?2 is null or c.createdTime <= ?2) " +
            "and (?3 = 0 or c.status = ?3)" +
            "and ( ?4 = 0 or c.customerId = ?4 )" +
            " order by c.createdTime desc")
    Page<Cart> filterDesc( Date startDate ,  Date endDate , int status, int cusId , Pageable pageable);

    @Query("select c from Cart c where c.deleted=false " +
            "and (?1 is null or c.createdTime >= ?1 ) and (?2 is null or c.createdTime <= ?2) " +
            "and (?3 = 0 or c.status = ?3)" +
            "and ( ?4 = 0 or c.customerId = ?4 )" +
            " order by c.createdTime asc")
    Page<Cart> filterAsc( Date startDate ,  Date endDate , int status, int cusId , Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Cart c set c.deleted = true where c.id = ?1")
    int deleteDataById(int id);

    @Transactional
    @Modifying
    @Query("update Cart c set c.status = ?2 where c.id = ?1")
    int setStatus(int id, int status);
}
