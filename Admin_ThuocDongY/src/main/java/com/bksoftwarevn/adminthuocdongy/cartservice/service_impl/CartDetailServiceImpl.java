package com.bksoftwarevn.adminthuocdongy.cartservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.CartDetail;
import com.bksoftwarevn.adminthuocdongy.cartservice.repository.CartDetailRepo;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl extends BaseServiceImpl<CartDetail, Integer , CartDetailRepo> implements CartDetailService   {

    private static final Logger logger = LoggerFactory.getLogger(CartDetailServiceImpl.class);

    public CartDetailServiceImpl(CartDetailRepo repo) {
        super(repo, "cart");
    }

    @Override
    public Optional<List<CartDetail>> findByIdCart(int id) throws Exception {
        try {
            return Optional.ofNullable(repo.findAllByIdCart(id));
        }catch (Exception ex){
            logger.error("find cart detail by cart-id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<CartDetail> findAll(Pageable pageable) {
        try {
            return repo.findAll(pageable);
        } catch (Exception ex) {
            logger.error("find all cart detail error : " + ex);
            ex.printStackTrace();
            return null;
        }
    }

}
