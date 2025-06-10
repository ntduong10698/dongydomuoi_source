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
public class PromoProductKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "khuyen_mai_id")
    private int promotionId;

    @Column(name = "san_pham_id")
    private int productId;


}
