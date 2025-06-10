package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke.HoaDonTrangThai;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.BillRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl extends BaseServiceImpl<Bill, Integer, BillRepo> implements BillService {

    private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    public BillServiceImpl(BillRepo repo) {
        super(repo, "bill");
    }

    @Override
    public Page<Bill> findByCustomerId(int cusId, Pageable pageable) {
        try {
            return repo.findByCustomerId(cusId, pageable);
        } catch (Exception ex) {
            logger.error("find Bill by customer err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<Bill> findByCode(String code) {
        try {
            return repo.findByCode(code);
        } catch (Exception ex) {
            logger.error("find Bill by customer err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        try {
            return repo.findAll(pageable);
        } catch (Exception ex) {
            logger.error("find all Bill err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void check(int id) {
        try {
             repo.check(id);
        } catch (Exception ex) {
            logger.error("check Bill err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void checkAll(int[] id) {
        try {
            repo.checkAll(id);
        } catch (Exception ex) {
            logger.error("check Bill err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Bill> filter(Date startDate, Date endDate, int cusId, int statusId, int sourceId, String code, String text, Pageable pageable, boolean dateAsc) {
        try {
            if (dateAsc)
                return repo.filterAsc(startDate, endDate, cusId, statusId, sourceId, code, text, pageable);
            else
                return repo.filterDesc(startDate, endDate, cusId, statusId, sourceId, code, text, pageable);
        } catch (Exception ex) {
            logger.error("filter Bill  err {0} ", ex);
            throw ex;
        }
    }

    @Override
    public Page<Bill> filter2(int comId, int statusId, String code, String text, Pageable pageable, boolean dateAsc) {
        try {
            if (dateAsc)
                return repo.filter2Asc(comId, statusId, code, text, pageable);
            else
                return repo.filter2Desc(comId, statusId, code, text, pageable);
        } catch (Exception ex) {
            logger.error("filter 2 Bill  err {0} ", ex);
            throw ex;
        }
    }

    @Override
    public long countBill(Date startDate, Date endDate) {
        try {
            return repo.countBill(startDate, endDate);
        } catch (Exception ex) {
            logger.error("cout Bill  err {0} ", ex);
            throw ex;
        }
    }

    @Override
    public int countUnchecked(int comId) {
        try {
            return repo.countUnChecked(comId);
        } catch (Exception ex) {
            logger.error("cout Bill  err {0} ", ex);
            throw ex;
        }
    }

    @Override
    public List<HoaDonTrangThai> hoaDonTrangThai(Date startDate, Date endDate) {
        try {
            return repo.hoaDonTrangThai(startDate, endDate);
        } catch (Exception ex) {
            logger.error("thong ke Bill status err {0}", ex);
            throw ex;
        }
    }

}
