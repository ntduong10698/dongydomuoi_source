package com.bksoftwarevn.adminthuocdongy.productservice.entities.promo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private int type; // 1: quà tặng, 2: giảm giá, 3: giảm phần trăm

    private String note;

    private String image;// optional

    private String content; // quà tặng

    private double decrease; // giảm giá hoặc giảm %

    private double minimum; // giá trị đơn hàng tối thiểu

    private double maxDiscount; // giảm tối đa hóa đơn
}
