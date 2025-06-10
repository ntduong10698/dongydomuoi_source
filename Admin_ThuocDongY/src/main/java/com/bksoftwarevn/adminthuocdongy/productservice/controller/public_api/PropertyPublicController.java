package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/properties")
//@CrossOrigin("http://localhost:6969")
public class PropertyPublicController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return propertyService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/product-type/{id}")
    public ResponseEntity<JsonResult> findByProductType(@PathVariable(name = "id") int id){
        try {
            return propertyService.findByProductType(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("product-type is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
