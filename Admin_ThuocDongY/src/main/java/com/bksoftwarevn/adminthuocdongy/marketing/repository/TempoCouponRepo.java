package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.TempoCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TempoCouponRepo extends JpaRepository<TempoCoupon, Integer> {

    @Query("from TempoCoupon x where x.token = ?1")
    Optional<TempoCoupon> findByToken(String token);

    @Query("select count (x) from TempoCoupon x where x.token = ?1")
    int checkToken(String token);

}
