package com.bksoftwarevn.adminthuocdongy.productservice.entities.key;

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
public class ProductLikeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "san_pham_id")
    private int productId;

    @Column(name = "user_id")
    private int userId;
}
