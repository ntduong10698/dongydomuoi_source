package com.bksoftwarevn.adminthuocdongy.marketing.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PopupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/popups")
public class PopupPublicController {

    @Autowired
    private PopupService popupService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findShowed(@PathVariable("id") int id){
        try {
            return JsonResult.success(popupService.findShowed(id));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
