package com.bksoftwarevn.adminthuocdongy.newsservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/news/v1/public/attachments")
public class NAttachmentPublicController {


    @Autowired
    private NAttachmentService NAttachmentService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return NAttachmentService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<JsonResult> findByNewsId(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(NAttachmentService.findByNews(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
