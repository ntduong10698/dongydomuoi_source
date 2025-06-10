package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/product-types")
public class ProductTypePublicController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
          return productTypeService.findById(id)
                  .map(JsonResult::success)
                  .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable(name = "id") int id, @RequestParam(value = "view", required = false) Boolean view){
        try {
            return productTypeService.findByCompany(view,id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("company is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
