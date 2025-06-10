package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductType;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CategoryService;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/product-type")
public class ProductTypeAdminController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody ProductType data){
        try {
            data.setDeleted(false);
            return productTypeService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody ProductType data){
        try {
            return productTypeService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (!categoryService.findByProductType(id).isEmpty())
                return JsonResult.badRequest("product type still contains category");
            if (productTypeService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
