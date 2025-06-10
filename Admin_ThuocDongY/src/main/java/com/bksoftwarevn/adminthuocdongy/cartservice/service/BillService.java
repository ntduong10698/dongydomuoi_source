package com.bksoftwarevn.adminthuocdongy.cartservice.service;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke.HoaDonTrangThai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillService extends BaseService<Bill> {

    Page<Bill> findByCustomerId(int cusId, Pageable pageable);

    Optional<Bill> findByCode(String code);

    Page<Bill> findAll(Pageable pageable);

    void check(int id);

    void checkAll(int[] id);

    Page<Bill> filter(Date startDate, Date endDate, int cusId, int statusId, int sourceId, String code, String text, Pageable pageable, boolean dateAsc);

    Page<Bill> filter2(int comId, int statusId, String code, String text, Pageable pageable, boolean dateAsc);

    long countBill(Date startDate, Date endDate);

    int countUnchecked(int comId);

    List<HoaDonTrangThai> hoaDonTrangThai(Date startDate, Date endDate);

}
