package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Brand;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.productservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/brands")
public class BrandPublicController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return brandService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable(name = "id") int id,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "20") int size){
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(new PageJson<Brand>(brandService.findByCompany(id, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
