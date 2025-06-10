package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductComment;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CommentService;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/public/comments")
public class CommentPublicController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return commentService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/product/{id}")
    public ResponseEntity<JsonResult> upload(@RequestBody ProductComment comment,
                                             @PathVariable(name = "id") int id){
        try {
            comment.setDeleted(false);
            comment.setAccepted(false);
            comment.setTime(new Date());
            comment.setProduct(productService.findById(id).orElse(null));
            if (comment.getProduct() == null)
                return JsonResult.badRequest("product is not exist");
            return JsonResult.success(commentService.save(comment));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<JsonResult> findByProduct(@PathVariable(name = "id") int id,
                                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "size", defaultValue = "5") int size){
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(new PageJson<ProductComment>(commentService.findByProductId(id, true, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
