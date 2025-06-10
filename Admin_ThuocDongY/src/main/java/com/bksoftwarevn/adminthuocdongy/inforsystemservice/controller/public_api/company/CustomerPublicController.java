package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/customers")
@AllArgsConstructor
public class CustomerPublicController {

    private final CustomerService customerService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable("id") int id){
        try {
            return JsonResult.success(customerService.findByCompany(id));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
