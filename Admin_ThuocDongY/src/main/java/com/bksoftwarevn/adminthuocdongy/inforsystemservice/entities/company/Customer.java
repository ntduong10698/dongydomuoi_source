package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "khach_hang")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String name;

    private String feedback;

    private String image;

    private boolean deleted;
}
