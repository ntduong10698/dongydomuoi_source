package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "chuong")
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int stt;

    private boolean deleted;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "khoa_hoc_id")
    private Course course;
}
