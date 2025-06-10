package com.bksoftwarevn.adminthuocdongy.newsservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/news/v1/public/categories")
public class NCategoryPublicController {

    @Autowired
    private NCategoryService NCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return NCategoryService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(NCategoryService.findByCompany(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/news/company/{id}")
    public ResponseEntity<JsonResult> findNewsByComId(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(NCategoryService.findByCompany(id).stream().filter(cate -> cate.getCode() == null));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
