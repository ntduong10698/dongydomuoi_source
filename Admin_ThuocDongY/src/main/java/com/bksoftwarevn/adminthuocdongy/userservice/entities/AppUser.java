package com.bksoftwarevn.adminthuocdongy.userservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String name;

    private String address;

    private String image;

    private boolean admin;

    private String note;

    private String feedback;

    private boolean enableFeedback;

    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    private int gender;

    private Boolean deleted;

    private int companyId;
}
