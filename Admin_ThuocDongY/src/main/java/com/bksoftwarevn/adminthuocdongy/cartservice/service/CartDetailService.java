package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.CartDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CartDetailService extends BaseService<CartDetail> {

    Optional<List<CartDetail>> findByIdCart(int id) throws Exception;

    Page<CartDetail> findAll(Pageable pageable);

}
