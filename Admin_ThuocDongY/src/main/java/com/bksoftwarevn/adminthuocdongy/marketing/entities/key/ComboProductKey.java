package com.bksoftwarevn.adminthuocdongy.marketing.entities.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComboProductKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "combo_id")
    private int comboId;

    @Column(name = "san_pham_id")
    private int productId;
}
