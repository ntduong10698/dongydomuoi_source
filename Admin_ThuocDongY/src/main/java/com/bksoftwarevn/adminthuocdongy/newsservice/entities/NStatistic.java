package com.bksoftwarevn.adminthuocdongy.newsservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "thong_ke_bai_viet")
public class NStatistic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int view;

    @ManyToOne
    @JoinColumn(name = "bai_viet_id")
    private News news;
}
