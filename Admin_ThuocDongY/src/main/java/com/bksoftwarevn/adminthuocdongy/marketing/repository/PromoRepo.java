package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromoRepo extends BaseRepo<Promotion, Integer> {

    @Query("from Promotion x where x.id = ?1 and x.deleted = false")
    Optional<Promotion> findDataById(int id);

    @Query("from Promotion x where x.companyId = ?1 and x.name like concat('%',?2,'%') and (?3 is null or x.global = ?3) and x.deleted = false order by x.id desc")
    Page<Promotion> findByCompanyId(int id, String text, Boolean global, Pageable pageable);

    @Query("from Promotion x where x.companyId = ?1 and x.global = true and x.deleted = false and (x.start = 0 or x.start is null or  (x.start <= ?2 and x.end >= ?2)) order by x.id desc ")
    List<Promotion> findGlobal(int comId, long now);

    @Query("select php.promotion from PromotionHasProduct php where php.id.productId = ?1 and php.promotion.deleted = false and (php.promotion.start = 0 or php.promotion.start is null or (php.promotion.start <= ?2 and php.promotion.end >= ?2)) ")
    List<Promotion> findByProduct(int productId, long now);

    @Query("select php.promotion from PromotionHasProduct php where php.id.productId = ?1 and php.promotion.deleted = false ")
    List<Promotion> findByProductNoCheck(int productId);

    @Modifying
    @Transactional
    @Query("update Promotion x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
