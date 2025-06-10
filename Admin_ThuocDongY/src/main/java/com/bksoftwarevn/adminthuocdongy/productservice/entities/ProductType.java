package com.bksoftwarevn.adminthuocdongy.productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "phan_loai")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String code;

    private String image;

    private String icon;

    private String attachment;

    private int stt;

    private boolean enableView;

    private String description;

    private String alias;

    private boolean deleted;

    private int companyId;
}
