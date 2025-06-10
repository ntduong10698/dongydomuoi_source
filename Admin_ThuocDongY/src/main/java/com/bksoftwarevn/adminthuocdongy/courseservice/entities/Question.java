package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "cau_hoi")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    private int type;

    private String answer;

    private int stt;

    private boolean deleted;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bai_hoc_id")
    private Lesson lesson;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cau_hoi_id")
    private List<QuestionOption> options;
}
