package com.bksoftwarevn.adminthuocdongy.cartservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/statuses")
public class StatusPublicController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<JsonResult> findAll(){
        try {
            return JsonResult.success(statusService.findAll());
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
