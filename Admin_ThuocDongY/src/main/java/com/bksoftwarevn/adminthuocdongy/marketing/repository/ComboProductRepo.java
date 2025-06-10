package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Combo;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.ComboHasProduct;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.ComboProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ComboProductRepo extends JpaRepository<ComboHasProduct, ComboProductKey> {

    @Query("select chp.combo from ComboHasProduct chp where chp.id.productId = ?1 and chp.combo.end > ?2")
    List<Combo> findByProduct(int productId,@Temporal Date now);

    @Query("select chp.id.productId from ComboHasProduct chp where chp.id.comboId = ?1")
    List<Integer> findProductByCombo(int comboId);
}
