package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.PromotionHasProduct;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.PromoProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoProductRepo extends JpaRepository<PromotionHasProduct, PromoProductKey> {

    @Query("select chp.id.productId from PromotionHasProduct chp where chp.id.promotionId = ?1")
    List<Integer> findProductByPromo(int promoId);
}
