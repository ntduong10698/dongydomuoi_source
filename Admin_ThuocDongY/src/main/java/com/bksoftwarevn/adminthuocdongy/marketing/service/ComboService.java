package com.bksoftwarevn.adminthuocdongy.marketing.service;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Combo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComboService extends BaseService<Combo>{

    Page<Combo> findByCompany(int comId, Pageable pageable);

    List<Combo> findByProduct(int productId);

    List<Integer> findProductByCombo(int comboId);

    void addProduct(int comboId, List<Integer> pIds);

    void removeProduct(int comboId, List<Integer> pIds);
}
