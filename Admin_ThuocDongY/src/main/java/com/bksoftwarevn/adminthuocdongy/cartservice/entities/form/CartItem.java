package com.bksoftwarevn.adminthuocdongy.cartservice.entities.form;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.CartDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private String codeCart;

    List<CartDetail> listCartDetail ;

    private int product_id;

    private int price_id;

}
