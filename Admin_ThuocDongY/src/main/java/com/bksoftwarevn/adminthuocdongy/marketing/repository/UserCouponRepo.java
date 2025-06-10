package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.UserHasCoupon;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.UserCouponKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponRepo extends JpaRepository<UserHasCoupon, UserCouponKey> {

    @Query("update UserHasCoupon x set x.used = x.used + 1 where x.id = ?1")
    int userCoupon(UserCouponKey id);
}
