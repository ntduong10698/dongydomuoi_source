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
@Table(name = "cong_ty")
public class Company {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name ="website")
    private String website;

    @Column(name = "representation_person")
    private String representationPerson;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "logo")
    private String logo;

    @Column(name = "description")
    private String description;

    private String slogan;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "fax")
    private String fax;

    private String map;

    private String workingTime;

    @Column(name = "deleted")
    private Boolean deleted;
}
