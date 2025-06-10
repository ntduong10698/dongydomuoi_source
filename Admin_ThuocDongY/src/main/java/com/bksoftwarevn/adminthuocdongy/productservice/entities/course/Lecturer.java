package com.bksoftwarevn.adminthuocdongy.productservice.entities.course;

import lombok.Data;

import java.io.Serializable;

@Data
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String introduction;

    private String image;

    private int companyId;

    private boolean deleted;
}
