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
public class CourseAdmin {

    private int id;

    private String name;

    private String alias;

    private String model;

    private double cost;

    private List<String> categories;

    private int quantity;

    private int view;

    private String image;

    private int status;

    private Object course;
}
