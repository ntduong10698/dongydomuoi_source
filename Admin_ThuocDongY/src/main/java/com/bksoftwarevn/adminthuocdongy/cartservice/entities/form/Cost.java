package com.bksoftwarevn.adminthuocdongy.cartservice.entities.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cost {

    private int id;

    private Double cost;

    private String unit;

    private double weight;

    private boolean defaultCost;
}
