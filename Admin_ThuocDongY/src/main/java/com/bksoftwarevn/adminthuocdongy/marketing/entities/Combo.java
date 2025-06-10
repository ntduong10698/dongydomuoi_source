package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "combo")
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private int type;

    private double decrease;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date start;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date end;

    private boolean enable;

    private int stt;

    private boolean deleted;

    private int companyId;
}
