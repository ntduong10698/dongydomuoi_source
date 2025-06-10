package com.bksoftwarevn.adminthuocdongy.cartservice.entities.key;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cart_detail")
public class CartDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double amount;

    @Column(name = "total_money")
    private Double totalMoney;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    private boolean deleted;

}
