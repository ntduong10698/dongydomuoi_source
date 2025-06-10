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
@Table(name = "thong_ke")
public class ProductStatistic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int viewed;

    private int sold;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "san_pham_id")
    private Product product;
}
