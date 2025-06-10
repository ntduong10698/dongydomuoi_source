package com.bksoftwarevn.adminthuocdongy.marketing.entities.json;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponView {

    public CouponView(Coupon coupon){
        this.code = coupon.getCode();
        this.content = coupon.getContent();
        this.type = coupon.getType();
        this.decrease =coupon.getDecrease();
        this.end = coupon.getEnd();
        this.minimum = coupon.getMinimum();
        this.maxDiscount = coupon.getMaxDiscount();
        this.quantity = coupon.getQuantity();
    }

    private String code;
    private String content;
    private int type;
    private double decrease;
    private double minimum;
    private double maxDiscount;
    private int quantity;
    private Date end;

}
