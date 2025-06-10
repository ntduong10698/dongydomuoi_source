package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/infos")
public class InfoPublicController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable(name = "id") int id){
        try {
            return JsonResult.success(infoService.findByComId(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

}
