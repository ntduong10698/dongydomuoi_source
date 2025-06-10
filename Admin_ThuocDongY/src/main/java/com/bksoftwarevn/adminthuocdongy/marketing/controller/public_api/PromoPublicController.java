package com.bksoftwarevn.adminthuocdongy.marketing.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.PromoView;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(value = {"https://dev.bksoftwarevn.com"}, methods = {RequestMethod.GET})
@RestController
@RequestMapping("api/v1/public/promotions")
public class PromoPublicController {

    @Autowired
    private PromoService promoService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            PromoView view = PromoView.builder()
                    .promotion(promoService.findById(id).get())
                    .products(promoService.findProductByPromo(id))
                    .build();
            return JsonResult.success(view);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<JsonResult> findByProductId(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(promoService.findByProduct(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<JsonResult> findByProductId(@RequestParam(name = "ids")List<Integer> proIds) {
        try {
            return JsonResult.success(promoService.findByProducts(proIds));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/company/{comId}")
    public ResponseEntity<JsonResult> findGlobal(@PathVariable("comId") int comId){
        try {
            return JsonResult.success(promoService.findGlobal(comId));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
