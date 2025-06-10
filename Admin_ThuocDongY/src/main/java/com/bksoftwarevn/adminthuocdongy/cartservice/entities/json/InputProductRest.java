package com.bksoftwarevn.adminthuocdongy.cartservice.entities.json;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.Cost;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.ProductForm;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.form.PromoForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputProductRest {

    private ProductForm product;

    private List<Cost> costs;

    private List<PromoForm> promotion;
}
