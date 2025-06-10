package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CartService extends BaseService<Cart> {

    Optional<List<Cart>> findByCustomer(int cusId) throws Exception;

    boolean setStatus(int id, int status);

    Page<Cart> findAll(Pageable pageable);

    Page<Cart> filter(Date startDate , Date endDate , int status, int cusId , Pageable pageable , boolean dateAsc);

}
