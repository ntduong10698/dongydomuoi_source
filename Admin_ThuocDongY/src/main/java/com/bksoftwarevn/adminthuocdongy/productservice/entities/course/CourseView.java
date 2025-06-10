package com.bksoftwarevn.adminthuocdongy.productservice.entities.course;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.promo.Promotion;
import lombok.Data;

import java.util.List;

@Data
public class CourseView {

    private int id;

    private String name;

    private double cost;

    private String alias;

    private List<Promotion> promotions;

    private String image;

    private String preview;

    private Course course;
}
