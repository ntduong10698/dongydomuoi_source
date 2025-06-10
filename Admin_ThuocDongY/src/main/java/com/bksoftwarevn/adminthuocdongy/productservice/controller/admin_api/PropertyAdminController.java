package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductProperty;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductTypeService;
import com.bksoftwarevn.adminthuocdongy.productservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/property")
public class PropertyAdminController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ProductTypeService productTypeService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody ProductProperty data,
                                             @RequestParam(name = "product-type") int id){
        try {
            data.setDeleted(false);
            data.setProductType(productTypeService.findById(id).orElse(null));
            if (data.getProductType() == null)
                return JsonResult.badRequest("product-type is not exist");
            return propertyService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody ProductProperty data){
        try {
            return propertyService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (propertyService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
