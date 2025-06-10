package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/branches")
public class BranchPublicController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable("id") int comId){
        try {
            return JsonResult.success(branchService.findByCompany(comId));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
