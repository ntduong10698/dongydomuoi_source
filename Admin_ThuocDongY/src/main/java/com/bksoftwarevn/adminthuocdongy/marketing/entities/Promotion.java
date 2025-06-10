package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "khuyen_mai")
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int type; // 1: quà tặng, 2: giảm giá, 3: giảm phần trăm

    private boolean global; // true: theo đơn hàng, false: theo sản phẩm

    private String note;

    private String image;// optional

    private String content; // quà tặng

    private double decrease; // giảm giá hoặc giảm %

    private double minimum; // giá trị đơn hàng tối thiểu

    private double maxDiscount; // giảm tối đa hóa đơn

    private Long start;

    private Long end;

    private boolean deleted;

    private int companyId;
}
