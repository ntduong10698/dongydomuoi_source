package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductComment;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/comment")
public class CommentAdminController {

    @Autowired
    private CommentService commentService;

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody ProductComment data){
        try {
            return commentService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (commentService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<JsonResult> findByProduct(@PathVariable(name = "id") int id,
                                                    @RequestParam(name = "accepted", required = false) Boolean accepted,
                                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "size", defaultValue = "5") int size){
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(new PageJson<ProductComment>(commentService.findByProductId(id, accepted, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
