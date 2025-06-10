package com.bksoftwarevn.adminthuocdongy.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "thuoc_tinh")
public class ProductProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int type;

    private String code;

    private boolean required;

    private String options;

    private boolean deleted;

    @Column(name = "default_value")
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "phan_loai_id")
    private ProductType productType;
}
