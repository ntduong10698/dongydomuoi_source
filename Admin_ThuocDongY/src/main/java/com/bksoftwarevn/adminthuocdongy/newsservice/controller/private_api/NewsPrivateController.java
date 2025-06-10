package com.bksoftwarevn.adminthuocdongy.newsservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.News;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCategoryService;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/news/v1/admin/news")
public class NewsPrivateController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NCategoryService NCategoryService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody News data,
                                             @RequestParam("category-id") int id){
        try {
            data.setDeleted(false);
            data.setCreatTime(new Date());
            data.setCategory(NCategoryService.findById(id).orElse(null));
            if (data.getCategory() == null)
                return JsonResult.badRequest("category is not exist");
            return newsService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody News data){
        try {
            return newsService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (newsService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
