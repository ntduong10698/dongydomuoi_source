package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "giang_vien")
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String position;

    private String introduction;

    private String image;

    private String image2;

    private String image3;

    private int companyId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modify;

    private boolean deleted;
}
