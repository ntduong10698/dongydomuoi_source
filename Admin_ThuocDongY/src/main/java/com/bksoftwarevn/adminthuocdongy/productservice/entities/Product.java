package com.bksoftwarevn.adminthuocdongy.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "san_pham")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String model;

    private int status; // 1 còn hàng, 2 hết hàng, 3 ngừng kinh doanh

    private int guarantee; // bảo hành

    private String preview; // tóm tắt

    private String introduction; // giới thiệu

    private String image;

    @Temporal(value = TemporalType.DATE)
    private Date createDate;

    private int quantity; // so luong san co

    private String alias;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "thuong_hieu_id")
    private Brand brand;

}
