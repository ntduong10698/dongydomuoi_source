package com.bksoftwarevn.adminthuocdongy.productservice.entities;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.ProductPropertyKey;
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
@Table(name = "san_pham_has_thuoc_tinh")
public class ProductHasProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductPropertyKey id;

    private String value;

    private boolean deleted;

//    @ManyToOne
//    @MapsId("productId")
//    @JoinColumn(name = "san_pham_id")
//    private Product product;

    @ManyToOne
    @MapsId("propertyId")
    @JoinColumn(name = "thuoc_tinh_id")
    private ProductProperty productProperty;
}
