package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.BillDetail;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.BillDetailRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.BillDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl extends BaseServiceImpl<BillDetail, Integer, BillDetailRepo> implements BillDetailService {

    private static final Logger logger = LoggerFactory.getLogger(CartDetailServiceImpl.class);

    public BillDetailServiceImpl(BillDetailRepo repo) {
        super(repo, "bill_detail");
    }

    @Override
    public List<BillDetail> findByBillId(int id) {
        try {
            return repo.findByBillId(id);
        } catch (Exception ex) {
            logger.error("find bill_detail by billId err {0} ", ex);
            return null;
        }
    }

    @Override
    public void saveAll(List<BillDetail> billDetails) {
        try {
            repo.saveAll(billDetails);
        } catch (Exception ex) {
            logger.error("save list bill detail err {0} ", ex);
            throw ex;
        }
    }
}
