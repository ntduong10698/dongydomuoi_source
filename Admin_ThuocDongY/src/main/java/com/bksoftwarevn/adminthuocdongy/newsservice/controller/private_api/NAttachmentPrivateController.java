package com.bksoftwarevn.adminthuocdongy.newsservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NAttachment;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.News;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NAttachmentService;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/news/v1/admin/attachment")
public class NAttachmentPrivateController {

    @Autowired
    private NAttachmentService NAttachmentService;

    @Autowired
    private NewsService newsService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody List<NAttachment> datas, @RequestParam("news-id") int newsId) {
        try {
            News news = newsService.findById(newsId).get();
            datas.forEach(data -> {
                data.setDeleted(false);
                data.setNews(news);
            });
            return JsonResult.success(NAttachmentService.saveAll(datas));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody NAttachment data) {
        try {
            return NAttachmentService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (NAttachmentService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
