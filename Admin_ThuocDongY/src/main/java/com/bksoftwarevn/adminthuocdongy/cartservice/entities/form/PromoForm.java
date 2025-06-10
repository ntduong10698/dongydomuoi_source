package com.bksoftwarevn.adminthuocdongy.cartservice.entities.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoForm implements Serializable {

    private int id;

    private String name;

    private int type; // 1 tang qua, 2 giam tien, 3 giam phan tram

    private double decrease;

    private String content;
    
    private double minimum;

    private double maxDiscount;

}
