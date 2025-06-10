package com.bksoftwarevn.adminthuocdongy.cartservice.entities.key;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bill_detail")
public class BillDetail {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    private int productId;

    private String productName;

    private String productImage;

    private String promotion;

    private String properties;

    private Double originPrice;

    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    private boolean deleted;
}
