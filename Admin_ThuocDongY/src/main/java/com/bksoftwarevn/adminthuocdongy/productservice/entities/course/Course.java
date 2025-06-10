package com.bksoftwarevn.adminthuocdongy.productservice.entities.course;

import lombok.Data;


@Data
public class Course {

    private int id;

    private int member;

    private int type;

    private int maxView;

    private boolean deleted;

    private Lecturer lecturer;
}
