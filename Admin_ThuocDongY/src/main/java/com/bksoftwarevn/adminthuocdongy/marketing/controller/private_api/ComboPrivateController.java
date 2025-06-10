package com.bksoftwarevn.adminthuocdongy.marketing.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Combo;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/combo")
public class ComboPrivateController {

    @Autowired
    private ComboService comboService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Combo data) {
        try {
            data.setDeleted(false);
            return comboService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Combo data) {
        try {
            return comboService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {

            if (comboService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<JsonResult> addProducts(@PathVariable(name = "id") int id,
                                                  @RequestParam(name = "ids") List<Integer> ids) {
        try {
            comboService.addProduct(id, ids);
            return JsonResult.success("add products success");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<JsonResult> deleteProducts(@PathVariable(name = "id") int id,
                                                     @RequestParam(name = "ids") List<Integer> ids) {
        try {
            comboService.removeProduct(id, ids);
            return JsonResult.success("remove products success");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
