package com.bksoftwarevn.adminthuocdongy.cartservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/carts")
public class CartPublicController {

    @Autowired
    private CartService cartService;




}
