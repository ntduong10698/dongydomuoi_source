package com.bksoftwarevn.adminthuocdongy.marketing.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Promotion;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.RestBuilder;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PromoService;
import com.bksoftwarevn.adminthuocdongy.marketing.service_impl.RestMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/promotion")
public class PromoPrivateController {

    @Autowired
    private PromoService promoService;

    @Autowired
    private RestMService restService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Promotion data) {
        try {
            data.setDeleted(false);
            return promoService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Promotion data) {
        try {
            return promoService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {

            if (promoService.delete(id))
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
            promoService.addProduct(id, ids);
            return JsonResult.success("add products success");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/{id}/category/{cate-id}")
    public ResponseEntity<JsonResult> addProductsOfCategory(@PathVariable(name = "id") int id,
                                                            @PathVariable(name = "cate-id") int cateId) {
        try {
            List<Integer> productIds = (List<Integer>) restService.callGet(RestBuilder.build()
                    .service("")
                    .uri("api/v1/public/products/category/" + cateId));
            promoService.addProduct(id, productIds);
            return JsonResult.success("add products success");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<JsonResult> deleteProducts(@PathVariable(name = "id") int id,
                                                     @RequestParam(name = "ids") List<Integer> ids) {
        try {
            promoService.removeProduct(id, ids);
            return JsonResult.success("remove products success");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
