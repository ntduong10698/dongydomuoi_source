package com.bksoftwarevn.adminthuocdongy.marketing.service_impl;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.*;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.ComboProductKey;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.ComboProductRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.repository.ComboRepo;
import com.bksoftwarevn.adminthuocdongy.marketing.service.ComboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ComboServiceImpl extends BaseServiceImpl<Combo, Integer, ComboRepo> implements ComboService {

    @Autowired
    private ComboProductRepo comboProductRepo;

    public ComboServiceImpl(ComboRepo repo) {
        super(repo, "Combo");
    }

    @Override
    public Page<Combo> findByCompany(int comId, Pageable pageable) {
        try {
            return repo.findByCompany(comId, pageable);
        } catch (Exception ex) {
            log.error("findByCompany error {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Combo> findByProduct(int productId) {
        try {
            return comboProductRepo.findByProduct(productId, new Date());
        } catch (Exception ex) {
            log.error(" findByProduct error {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Integer> findProductByCombo(int comboId) {
        try {
            return comboProductRepo.findProductByCombo(comboId);
        } catch (Exception ex) {
            log.error(" findProductByCombo error {0}", ex);
            throw ex;
        }
    }

    @Override
    public void addProduct(int comboId, List<Integer> pIds) {
        try {
            Combo combo = repo.findDataById(comboId).get();
            pIds.forEach(pId -> {
                ComboHasProduct chp = new ComboHasProduct();
                chp.setCombo(combo);
                chp.setId(new ComboProductKey(comboId, pId));
                comboProductRepo.save(chp);
            });
        } catch (Exception ex) {
            log.error("addProduct to combo err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void removeProduct(int comboId, List<Integer> pIds) {

    }
}
