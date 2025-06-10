package com.bksoftwarevn.adminthuocdongy.productservice.entities;


import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.ProductLikeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "san_pham_yeu_thich")
public class ProductLiked implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductLikeKey id;

    private boolean deleted;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "san_pham_id")
    private Product product;
}
