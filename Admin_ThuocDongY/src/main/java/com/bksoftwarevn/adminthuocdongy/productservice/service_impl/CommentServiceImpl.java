package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductComment;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.CommentRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<ProductComment,Integer, CommentRepo> implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepo repo) {
        super(repo, "Comment");
    }

    @Override
    public Page<ProductComment> findByProductId(int comId, Boolean accepted,Pageable pageable) {
        try {
            return repo.findByProductId(comId, accepted, pageable);
        }catch (Exception ex){
            logger.error("find comment by product err {0}", ex);
            throw ex;
        }
    }
}
