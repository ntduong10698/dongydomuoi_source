package com.bksoftwarevn.adminthuocdongy.cartservice.entities.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailForm {

    private int productId;

    private int priceId;

    private int number;

    private List<PropertyForm> properties;

}
