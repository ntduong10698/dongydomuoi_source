package com.bksoftwarevn.adminthuocdongy.newsservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NCategory;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCategoryService;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/news/v1/admin/category")
public class NCategoryPrivateController {

    @Autowired
    private NCategoryService NCategoryService;

    @Autowired
    private NewsService newsService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody NCategory data){
        try {
            data.setDeleted(false);
            return NCategoryService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody NCategory data){
        try {
            return NCategoryService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (newsService.countByCate(id) > 0)
                return JsonResult.badRequest("category still contains news");
            if (NCategoryService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
