package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.SourceOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SourceOrderService extends BaseService<SourceOrder>{

    Page<SourceOrder> findAll(Pageable pageable, boolean ascName);

    Page<SourceOrder> filter(String text, Pageable pageable, boolean ascName);

    Optional<SourceOrder> findDefault(int comId);

}
