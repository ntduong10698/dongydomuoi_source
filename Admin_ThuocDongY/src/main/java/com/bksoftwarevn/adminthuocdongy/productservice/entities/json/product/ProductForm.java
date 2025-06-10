package com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductForm implements Serializable {

    private String name;

    private String model;

    private int guarantee; // bảo hành

    private String preview; // tóm tắt

    private String introduction; // giới thiệu

    private String alias;

    private String image;

    private int quantity;

    private int brandId;

    private double cost;

    private String unit;

    private double weight;

    List<Integer> categoryIds;
}
