package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.UserCouponKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tai_khoan_has_coupon")
public class UserHasCoupon implements Serializable {

    @EmbeddedId
    private UserCouponKey id;

    private int used;

    @ManyToOne
    @MapsId("couponId")
    @JoinColumn(name = "ma_khuyen_mai_id")
    private Coupon coupon;
}
