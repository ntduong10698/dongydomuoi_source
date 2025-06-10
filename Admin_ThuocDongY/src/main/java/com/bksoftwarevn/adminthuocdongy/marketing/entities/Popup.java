package com.bksoftwarevn.adminthuocdongy.marketing.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "popup")
public class Popup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String url;

    private String link;

    private boolean active;

    private boolean deleted;

    private int companyId;
}
