package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.BillDetail;

import java.util.List;

public interface BillDetailService extends BaseService<BillDetail> {

    List<BillDetail> findByBillId(int id);

    void saveAll(List<BillDetail> billDetails);
}
