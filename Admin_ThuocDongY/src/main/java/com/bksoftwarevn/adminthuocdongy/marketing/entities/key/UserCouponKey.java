package com.bksoftwarevn.adminthuocdongy.marketing.entities.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCouponKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ma_khuyen_mai_id")
    private int couponId;
}
