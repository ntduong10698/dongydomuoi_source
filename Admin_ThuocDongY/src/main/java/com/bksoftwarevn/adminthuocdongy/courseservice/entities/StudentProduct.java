package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "san_pham_hoc_vien")
public class StudentProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String image;

    private boolean deleted;

    private boolean enableIndex;
    private boolean enableOnline;
    private boolean enableOffline;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;
}
