package com.bksoftwarevn.adminthuocdongy.newsservice.repository;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NAttachment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NAttachmentRepo extends BaseRepo<NAttachment, Integer> {

    @Query("from NAttachment x where x.id = ?1 and x.deleted = false")
    Optional<NAttachment> findDataById(int id);


    @Query("from NAttachment x where x.news.id = ?1 and x.deleted = false order by x.id desc ")
    List<NAttachment> findByNews(int id);

    @Modifying
    @Transactional
    @Query("update NAttachment x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
