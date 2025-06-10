package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Category;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductType;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CategoryService;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/category")
public class CategoryAdminController {

    @Autowired
    private CategoryService service;
    @Autowired
    private ProductTypeService productTypeService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Category data,
                                             @RequestParam(name = "product-type", required = false) Integer productTypeId){
        try {
            if (productTypeId != null){
                Optional<ProductType> productType = productTypeService.findById(productTypeId);
                if (productType.isPresent())
                    data.setProductType(productType.get());
                else return JsonResult.badRequest("product-type is not exist");
            }

            if (data.getParentId() != null){
                if (!service.addChildToParent(data.getParentId(),data))
                    return JsonResult.error(new Exception());

            }
            data.setDeleted(false);
            data.setSmallest(true);
            return service.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Category data,
                                             @RequestParam(name = "change-parent", defaultValue = "false", required = false) Boolean change){
        try {
            if (change && data.getParentId() != null){
                if (!service.addChildToParent(data.getParentId(), data))
                    return JsonResult.error(new Exception());
            }
            return service.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (!service.findByParent(id).isEmpty())
                return JsonResult.badRequest("cant delete a parent");
            if (service.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
