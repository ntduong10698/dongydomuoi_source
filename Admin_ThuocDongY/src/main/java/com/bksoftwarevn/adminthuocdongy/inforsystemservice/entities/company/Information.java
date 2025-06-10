package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thong_tin")
public class Information {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "deleted")
    private Boolean deleted;

}
