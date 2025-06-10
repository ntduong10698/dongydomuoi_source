package com.bksoftwarevn.adminthuocdongy.cartservice.entities.key;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "status")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_status")
    private String nameStatus;

    private Boolean deleted;
}
