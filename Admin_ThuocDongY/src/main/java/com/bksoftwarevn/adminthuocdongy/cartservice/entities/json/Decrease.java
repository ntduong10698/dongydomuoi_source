package com.bksoftwarevn.adminthuocdongy.cartservice.entities.json;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Decrease {

    private int productId;

    private int number;
}
