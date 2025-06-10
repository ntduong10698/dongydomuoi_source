package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/banks")
public class BankPublicController {

    @Autowired
    private BankService bankService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable("id") int id){
        try {
            return JsonResult.success(bankService.findByCompany(id));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
