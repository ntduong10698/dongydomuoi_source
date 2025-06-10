package com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAdmin {

    private int id;

    private String name;

    private String model;

    private String alias;

    private double cost;

    private String unit;

    private List<String> categories;

    private int quantity;

    private int view;

    private int sold;

    private String image;

    private int status;
}
