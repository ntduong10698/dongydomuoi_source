package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.key.PromoProductKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "khuyen_mai_theo_san_pham")
public class PromotionHasProduct implements Serializable {

    @EmbeddedId
    private PromoProductKey id;

    @ManyToOne
    @MapsId("promotionId")
    @JoinColumn(name = "khuyen_mai_id")
    private Promotion promotion;
}
