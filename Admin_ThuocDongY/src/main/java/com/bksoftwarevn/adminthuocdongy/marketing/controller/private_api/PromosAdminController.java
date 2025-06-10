package com.bksoftwarevn.adminthuocdongy.marketing.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/promotions")
public class PromosAdminController {

    @Autowired
    private PromoService promoService;

    @GetMapping("/company/{comId}")
    public ResponseEntity<JsonResult> findByCompany(@PathVariable("comId") int comId,
                                                    @RequestParam(value = "text", required = false, defaultValue = "") String textSearch,
                                                    @RequestParam(name = "global", required = false) Boolean global,
                                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return JsonResult.success(promoService.findByCompanyId(comId, textSearch, global, PageRequest.of(page - 1, size)));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<JsonResult> findByProduct(@PathVariable("id") int proId) {
        try {
            return JsonResult.success(promoService.findByProductNoCheck(proId));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
