package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.CartDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepo  extends BaseRepo<CartDetail,Integer> {

    @Query("from CartDetail c where c.deleted = false and c.cart.id = ?1  order by c.id ")
    List<CartDetail> findAllByIdCart(int cusId);

    @Query(value = " select c from CartDetail c where c.deleted = false order by c.id  ")
    Page<CartDetail> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update CartDetail c set c.deleted = true where c.id = ?1")
    int deleteDataById(int id);

    @Query("from CartDetail c where c.deleted = false and c.id = ?1")
    Optional<CartDetail> findDataById(int id);


}
