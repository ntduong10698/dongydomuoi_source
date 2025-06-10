package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lien_he")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String phone;

    private String title;

    private String content;

    private String email;

    private String attachment;

    private boolean checked;

    private boolean deleted;

    private int companyId;
}
