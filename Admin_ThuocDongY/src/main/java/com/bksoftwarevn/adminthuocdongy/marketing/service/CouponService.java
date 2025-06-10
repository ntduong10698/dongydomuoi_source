package com.bksoftwarevn.adminthuocdongy.marketing.service;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Coupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.UserCouponKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CouponService extends BaseService<Coupon>{

    Page<Coupon> findByCompanyId(int id, String text, Pageable pageable);

    List<Coupon> findGlobal(int id);

    List<Coupon> findByUser(int id);

    Optional<Coupon> findByCode(String code, int comId);

    void addUser(int couponId, List<Integer> userIds);

    boolean userUseCoupon(UserCouponKey id);
}
