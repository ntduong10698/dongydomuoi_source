package com.bksoftwarevn.adminthuocdongy.cartservice.entities.key;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "source_order")
public class SourceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_source_order")
    private String nameSourceOrder;

    private Boolean deleted;

    @Column(name = "default_value")
    private boolean defaultValue;

    private int companyId;
}
