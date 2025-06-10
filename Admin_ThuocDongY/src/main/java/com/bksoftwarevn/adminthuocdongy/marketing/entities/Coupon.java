package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ma_khuyen_mai")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private int type; //  1: giảm giá, 2: giảm phần trăm, 3: free ship

    private String content;

    private boolean global; // true: cho tất cả, false: theo user

    private double decrease; // giảm giá hoặc giảm %

    private double minimum; // giá trị đơn hàng tối thiểu

    private double maxDiscount; // giảm tối đa hóa đơn

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date end;

    private int quantity;

    private int used;

    private boolean deleted;

    private int companyId;
}
