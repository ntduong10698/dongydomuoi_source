package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/documents")
@AllArgsConstructor
public class DocumentPublicController {

    private final DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return documentService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(documentService.findByCompany(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
