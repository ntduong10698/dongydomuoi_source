package com.bksoftwarevn.adminthuocdongy.newsservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NComment;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NCommentRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class NCommentServiceImpl extends BaseServiceImpl<NComment, Integer, NCommentRepo> implements NCommentService {

    private static final Logger logger = LoggerFactory.getLogger(NCommentServiceImpl.class);

    public NCommentServiceImpl(NCommentRepo repo) {
        super(repo, "comment");
    }

    @Override
    public Page<NComment> findByNews(int newsId, Boolean accepted, Pageable pageable) {
        try {
            return repo.findByNews(newsId, accepted, pageable);
        }catch (Exception ex){
            logger.error("find comment by news err {0}", ex);
            throw ex;
        }
    }
}
