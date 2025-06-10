package com.bksoftwarevn.adminthuocdongy.newsservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NComment;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/news/v1/admin/comment")
public class NCommentPrivateController {


    @Autowired
    private NCommentService NCommentService;

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody NComment data){
        try {
            return NCommentService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<JsonResult> findByNewsId(@PathVariable(name = "id") int id,
                                                  @RequestParam(name = "accepted", required = false) Boolean accepted,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "5") int size){
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(new PageJson<>(NCommentService.findByNews(id,accepted, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (NCommentService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
