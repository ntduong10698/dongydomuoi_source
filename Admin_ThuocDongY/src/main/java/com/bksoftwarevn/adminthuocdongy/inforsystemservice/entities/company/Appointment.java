package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "dat_lich")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Boolean gender;

    private String phone;

    private String address;

    private String reason;

    private long time;

    private boolean deleted;

    private int status; // 0 - mới, 1 - đã xác nhận, 2 - đã gặp

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "chi_nhanh_id")
    private Branch branch;


}
