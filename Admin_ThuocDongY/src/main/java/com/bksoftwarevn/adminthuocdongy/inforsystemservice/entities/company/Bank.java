package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ngan_hang")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String bank;

    private String account;

    private String bankNumber;

    private boolean deleted;
}
