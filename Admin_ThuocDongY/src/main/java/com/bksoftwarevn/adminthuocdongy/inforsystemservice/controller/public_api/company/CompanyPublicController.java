package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/companies")
public class CompanyPublicController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable("id") int id){
        try {
            return companyService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("compnay-not-found"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
