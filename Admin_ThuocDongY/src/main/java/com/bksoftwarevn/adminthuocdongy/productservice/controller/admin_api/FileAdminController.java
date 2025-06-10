package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductFile;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.FileService;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/files")
public class FileAdminController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductService productService;

    @PostMapping("/product/{id}")
    public ResponseEntity<JsonResult> upload(@RequestBody List<ProductFile> datas,@PathVariable("id") int proId){
        try {
            List<ProductFile> responses = new ArrayList<>();
            Product product = productService.findById(proId).orElse(null);
            if (product == null)
                return JsonResult.badRequest("product is not exist");
            datas.forEach(data -> {
                data.setDeleted(false);
                data.setProduct(product);
                try {
                    responses.add(fileService.save(data).orElse(null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return JsonResult.uploaded(responses);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody List<ProductFile> datas){
        try {
            datas.forEach(data -> {
                try {
                    fileService.save(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return JsonResult.updated(datas);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<JsonResult> delete(@RequestParam(name = "ids") List<Integer> ids){
        try {
            ids.forEach(id -> {
                try {
                    fileService.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return JsonResult.deleted();
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
