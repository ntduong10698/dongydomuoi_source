package com.bksoftwarevn.adminthuocdongy.marketing.service;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PromoService extends BaseService<Promotion> {

    Page<Promotion> findByCompanyId(int id, String text, Boolean global, Pageable pageable);

    List<List<Promotion>> findByProducts(List<Integer> proIds);

    List<Promotion> findByProduct(int id);

    List<Promotion> findByProductNoCheck(int id);

    List<Promotion> findGlobal(int comId);

    List<Integer> findProductByPromo(int promoId);

    void addProduct(int promoId, List<Integer> pIds);

    void removeProduct(int promoId, List<Integer> pIds);
}
