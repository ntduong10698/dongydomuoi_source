package com.bksoftwarevn.adminthuocdongy.cartservice.controller.public_api;


import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/cart-details")
public class CartDetailPublicController {

    @Autowired
    private CartDetailService cartDetailService ;



}
