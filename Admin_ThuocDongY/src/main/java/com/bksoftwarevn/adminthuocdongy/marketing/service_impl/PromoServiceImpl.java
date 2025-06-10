package com.bksoftwarevn.adminthuocdongy.marketing.service_impl;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Promotion;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.PromotionHasProduct;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.PromoProductKey;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.PromoProductRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.PromoRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PromoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PromoServiceImpl extends BaseServiceImpl<Promotion, Integer, PromoRepo> implements PromoService {

    @Autowired
    private PromoProductRepo promoProductRepo;

    public PromoServiceImpl(PromoRepo repo) {
        super(repo, "Promotion");
    }

    @Override
    public Page<Promotion> findByCompanyId(int id, String text, Boolean global, Pageable pageable) {
        try {
            return repo.findByCompanyId(id, text, global, pageable);
        } catch (Exception ex) {
            log.error("find Promo by company err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<List<Promotion>> findByProducts(List<Integer> proIds) {
        try {
            List<List<Promotion>> listList = new ArrayList<>();
            proIds.forEach(id -> listList.add(repo.findByProduct(id, new Date().getTime())));
            return listList;
        } catch (Exception ex) {
            log.error("find Promo by products err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Promotion> findByProduct(int productId) {
        try {
            return repo.findByProduct(productId, new Date().getTime());
        } catch (Exception ex) {
            log.error("find Promo by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Promotion> findByProductNoCheck(int id) {
        try {
            return repo.findByProductNoCheck(id);
        } catch (Exception ex) {
            log.error("find Promo by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Promotion> findGlobal(int comId) {
        try {
            return repo.findGlobal(comId, new Date().getTime());
        } catch (Exception ex) {
            log.error("find Promo global err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Integer> findProductByPromo(int promoId) {
        try {
            return promoProductRepo.findProductByPromo(promoId);
        } catch (Exception ex) {
            log.error("findProductByPromo err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void addProduct(int promoId, List<Integer> pIds) {
        try {
            Promotion promotion = repo.findDataById(promoId).get();
            pIds.forEach(pId -> {
                PromotionHasProduct php = new PromotionHasProduct();
                php.setPromotion(promotion);
                php.setId(new PromoProductKey(promoId, pId));
                promoProductRepo.save(php);
            });
        } catch (Exception ex) {
            log.error("addProduct to promo err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void removeProduct(int promoId, List<Integer> pIds) {
        try {
            pIds.forEach(pId -> promoProductRepo.deleteById(new PromoProductKey(promoId, pId)));
        } catch (Exception ex) {
            log.error("removeProduct from promo err {0}", ex);
            throw ex;
        }
    }
}
