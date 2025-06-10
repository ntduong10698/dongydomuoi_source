package com.bksoftwarevn.adminthuocdongy.newsservice.entities;

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
@Table(name = "dinh_kem_bai_viet")
public class NAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String url;

    private boolean deleted;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bai_viet_id")
    private News news;
}
