package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/files")
public class FilePublicController {

    @Autowired
    private FileService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return fileService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/product/{id}")
    @ApiOperation(value = "find files by product", notes = "1: image, 2: doc")
    public ResponseEntity<JsonResult> findByProduct(@PathVariable(name = "id") int id,
                                                    @RequestParam(value = "type", required = false, defaultValue = "0") int type){
        try {
            return JsonResult.success(fileService.findByProduct(id, type));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
