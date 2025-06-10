package com.bksoftwarevn.adminthuocdongy.newsservice.repository;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface NCommentRepo extends BaseRepo<NComment, Integer> {

    @Query("from NComment x where x.id = ?1 and x.deleted = false")
    Optional<NComment> findDataById(int id);

    @Query("from NComment x where x.news.id = ?1 and (x.accepted = ?2 or ?2 is null) and x.deleted = false order by x.id desc ")
    Page<NComment> findByNews(int newsId, Boolean accepted, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update NComment x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
