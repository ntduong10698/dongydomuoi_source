package com.bksoftwarevn.adminthuocdongy.newsservice.service;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NCommentService extends BaseService<NComment> {

    Page<NComment> findByNews(int newsId, Boolean accepted, Pageable pageable);
}
