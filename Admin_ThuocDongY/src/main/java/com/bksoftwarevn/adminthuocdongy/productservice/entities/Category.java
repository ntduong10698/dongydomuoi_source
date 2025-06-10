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
@Table(name = "danh_muc")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean enable;

    private String name;

    private String image;

    private String icon;

    private String attachment;

    private String description;

    private int stt;

    private boolean smallest;

    private String alias;

    private boolean deleted;

    private Integer parentId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "phan_loai_id")
    private ProductType productType;
}
