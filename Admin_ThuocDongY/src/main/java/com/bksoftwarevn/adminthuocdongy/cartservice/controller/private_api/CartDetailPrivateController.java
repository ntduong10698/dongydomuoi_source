package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Cart;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.CartDetail;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartDetailService;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/cart-detail")
public class CartDetailPrivateController {

    @Autowired
    private CartDetailService cartDetailService ;

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return cartDetailService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<JsonResult> findByIdCart(@PathVariable(name = "id") int id){
        try {
            return cartDetailService.findByIdCart(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("cart is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("id")int id) throws Exception {
        boolean bol=cartDetailService.delete(id);
        try {
            if(bol)
                return JsonResult.deleted();
            return JsonResult.badRequest("Delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestParam(name = "cart-id") int cartId,
                                             @RequestBody List<CartDetail> cartDetails){
        try {
            Cart cart = cartService.findById(cartId).get();
            for(int i = 0; i < cartDetails.size(); i++){
                CartDetail cartDetail = cartDetails.get(i);
                cartDetail.setCart(cart);
                cartDetail.setDeleted(false);
                try{
                    cartDetailService.save(cartDetail);
                }catch (Exception ex){
                    return JsonResult.error(ex);
                }
            }
            return JsonResult.success("Add cart detail success");
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

}
