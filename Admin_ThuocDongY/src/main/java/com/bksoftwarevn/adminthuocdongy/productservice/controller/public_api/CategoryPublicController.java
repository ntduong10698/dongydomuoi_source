package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/categorys")
public class CategoryPublicController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product-type/{id}")
    public ResponseEntity<JsonResult> findByPT(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(categoryService.findByProductType(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<JsonResult> findByParent(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(categoryService.findByParent(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return categoryService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
