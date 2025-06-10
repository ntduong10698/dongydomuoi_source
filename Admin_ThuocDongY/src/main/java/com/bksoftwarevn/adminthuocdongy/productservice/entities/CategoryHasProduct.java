package com.bksoftwarevn.adminthuocdongy.productservice.entities;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.CategoryProductKey;
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
@Table(name = "danh_muc_has_san_pham")
public class CategoryHasProduct implements Serializable {

    @EmbeddedId
    private CategoryProductKey id;

    private boolean deleted;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "san_pham_id")
    private Product product;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "danh_muc_id")
    private Category category;
}
