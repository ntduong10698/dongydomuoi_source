package com.bksoftwarevn.adminthuocdongy.newsservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NComment;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NCommentService;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/news/v1/public/comments")
public class NCommentPublicController {

    @Autowired
    private NCommentService NCommentService;

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return NCommentService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<JsonResult> findByNewsId(@PathVariable(name = "id") int id,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "5") int size){
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(new PageJson<>(NCommentService.findByNews(id,true, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody NComment data,
                                             @RequestParam("news-id") int id){
        try {
            data.setDeleted(false);
            data.setAccepted(false);
            data.setNews(newsService.findById(id).orElse(null));
            if (data.getNews() == null)
                return JsonResult.badRequest("news is not exist");
            return NCommentService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
