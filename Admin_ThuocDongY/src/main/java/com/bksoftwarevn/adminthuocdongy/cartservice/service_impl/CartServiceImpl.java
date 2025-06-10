package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Cart;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.CartRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl extends BaseServiceImpl<Cart , Integer , CartRepo> implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public CartServiceImpl(CartRepo repo) {
        super(repo, "cart");
    }

    @Override
    public Optional<List<Cart>> findByCustomer(int cusId) throws Exception {
        try {
            return Optional.ofNullable(repo.findByCustomerId(cusId));
        }catch (Exception ex){
            logger.error("find Cart by customer err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean setStatus(int id, int status) {
        try {
            return repo.setStatus(id, status) > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("set status err {0}", ex);
            return false;
        }
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        try {
            return repo.findAll(pageable);
        } catch (Exception ex) {
            logger.error("find all cart error : " + ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Cart> filter(Date startDate , Date endDate , int status, int cusId , Pageable pageable , boolean dateAsc) {
        try {
            if (dateAsc)
                return repo.filterAsc( startDate, endDate, status, cusId, pageable);
            else
                return repo.filterDesc( startDate, endDate, status, cusId, pageable);
        } catch (Exception ex) {
            logger.error("find cart filter err {0}", ex);
            throw ex;
        }
    }



}
