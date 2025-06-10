package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepo extends JpaRepository<ProductStatistic, Integer> {

    @Query("from ProductStatistic ps where ps.product.id = ?1 and ps.date >= ?2 and ps.date <= ?3")
    List<ProductStatistic> findByProduct(int id, Date start, Date end);

    @Query("select ps from ProductStatistic ps join CategoryHasProduct chp on ps.product.id = chp.product.id" +
            " where chp.category.productType.id = ?1 and ps.date >= ?2 and ps.date <= ?3")
    List<ProductStatistic> findByProductType(int typeId, Date from, Date to);

    @Query("from ProductStatistic ps where ps.product.id = ?1 and ps.date is null")
    Optional<ProductStatistic> findTotal(int proId);

    @Query("from ProductStatistic ps where ps.product.id = ?1 and ps.date = ?2")
    Optional<ProductStatistic> findByDate(int productId, Date today);

    @Modifying
    @Transactional
    @Query("update ProductStatistic ps set ps.viewed = (ps.viewed + 1) where ps.product.id = ?1 and ps.date = ?2")
    int increaseViewed(int productId, Date today);

    @Modifying
    @Transactional
    @Query("update ProductStatistic ps set ps.viewed = (ps.viewed + 1) where ps.product.id = ?1 and ps.date is null")
    int increaseViewedTotal(int productId);

    @Modifying
    @Transactional
    @Query("update ProductStatistic ps set ps.sold = (ps.sold + ?2) where ps.product.id = ?1 and ps.date = ?3")
    int increaseSold(int productId, int amount, Date today);

    @Modifying
    @Transactional
    @Query("update ProductStatistic ps set ps.sold = (ps.sold + ?2) where ps.product.id = ?1 and ps.date is null")
    int increaseSoldTotal(int productId, int amount);
}
