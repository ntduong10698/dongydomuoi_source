package com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.promo.Promotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductView {

    private int id;

    private String name;

    private double cost;

    private String alias;

    private List<Promotion> promotions;

    private String unit;

    private String image;

    private String preview;

    private int quantity;

}
