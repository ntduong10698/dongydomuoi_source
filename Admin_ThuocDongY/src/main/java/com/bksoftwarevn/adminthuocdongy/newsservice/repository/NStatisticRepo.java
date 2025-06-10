package com.bksoftwarevn.adminthuocdongy.newsservice.repository;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NStatisticRepo extends JpaRepository<NStatistic,Integer> {

    @Query("from NStatistic ps where ps.news.id = ?1 and ps.date >= ?2 and ps.date <= ?3")
    List<NStatistic> findByNews(int id, Date start, Date end);

    @Query("from NStatistic ps where ps.news.id in (?1) and ps.date >= ?2 and ps.date <= ?3")
    List<NStatistic> findByMultiNews(List<Integer> ids, Date start, Date end);

    Optional<NStatistic> findByDate(Date date);

    @Modifying
    @Transactional
    @Query("update NStatistic x set x.view = (x.view + 1) where x.news.id = ?1 and x.date = ?2")
    int increaseView(int productId, Date today);
}
