package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepo extends BaseRepo<Coupon, Integer> {

    @Query("from Coupon x where x.id = ?1 and x.deleted = false")
    Optional<Coupon> findDataById(int id);

    @Query("from Coupon x where x.code = ?1 and x.companyId = ?2 and x.deleted = false")
    Optional<Coupon> findDataByCode(String code, int comId);

    @Query("from Coupon x where x.companyId = ?1 and x.content like concat('%',?2,'%') and x.deleted = false order by x.id desc")
    Page<Coupon> findByCompanyId(int id, String text, Pageable pageable);

    @Query("from Coupon x where x.companyId = ?1 and x.deleted = false and x.global = true and x.used < x.quantity and (x.end is null or x.end > ?2)")
    List<Coupon> findGlobal(int comId, @Temporal(value = TemporalType.TIMESTAMP) Date now);

    @Query("select x.coupon from UserHasCoupon x where x.id.userId = ?1 and x.coupon.end > ?2 and x.coupon.deleted = false")
    List<Coupon> findByUser(int userId, @Temporal(value = TemporalType.TIMESTAMP) Date now);

    @Modifying
    @Transactional
    @Query("update Coupon x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
