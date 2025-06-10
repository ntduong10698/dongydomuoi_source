package com.bksoftwarevn.adminthuocdongy.marketing.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.ComboView;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.marketing.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/combos")
public class ComboPublicController {

    @Autowired
    private ComboService comboService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable(name = "id") int id,
                                                @RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "6") int size) {
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            return JsonResult.success(new PageJson<>(comboService.findByCompany(id, pageable)));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            ComboView view = ComboView.builder()
                    .combo(comboService.findById(id).get())
                    .products(comboService.findProductByCombo(id))
                    .build();
            return JsonResult.success(view);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
