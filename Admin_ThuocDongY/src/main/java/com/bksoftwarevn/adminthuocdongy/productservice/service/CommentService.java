package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService extends BaseService<ProductComment>{

    Page<ProductComment> findByProductId(int comId, Boolean accepted, Pageable pageable);

}
