package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "khoa_hoc")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int member;

    private int type;

    private int maxView;

    private boolean deleted;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "giang_vien_id")
    private Lecturer lecturer;
}
