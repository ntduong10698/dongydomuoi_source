package com.bksoftwarevn.adminthuocdongy.productservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.UserJson;
import com.bksoftwarevn.adminthuocdongy.productservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
public class ProductPrivateController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductLikedService productLikedService;

    @GetMapping("/favorite")
    public ResponseEntity<JsonResult> findProductLikedByUser(HttpServletRequest request) {
        try {
            UserJson userJson = (UserJson) request.getAttribute("user");
            return JsonResult.success(productLikedService.findByUser(userJson.getId()));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/favorite")
    public ResponseEntity<JsonResult> addProductLikedByUser(HttpServletRequest request,
                                                            @RequestParam(name = "products") List<Integer> productIds) {
        try {
            UserJson userJson = (UserJson) request.getAttribute("user");
            List<Product> products = productIds.stream().map(pId -> {
                try {
                    return productService.findById(pId).orElse(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());

            productLikedService.save(userJson.getId(), products);
            return JsonResult.uploaded("OK");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<JsonResult> removeProductLikedByUser(HttpServletRequest request,
                                                               @RequestParam(name = "products") List<Integer> products) {
        try {
            UserJson userJson = (UserJson) request.getAttribute("user");
            productLikedService.delete(userJson.getId(), products);
            return JsonResult.deleted();
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
