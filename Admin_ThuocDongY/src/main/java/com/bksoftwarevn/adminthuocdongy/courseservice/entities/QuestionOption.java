package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "lua_chon")
public class QuestionOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private int stt;

    private boolean correct;

    @Column(name = "cau_hoi_id")
    private int questionId;
}
