package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Bank;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BankService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class BankAdminController {

    @Autowired
    private BankService bankService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/banks/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable("id") int id) {
        try {
            return JsonResult.success(bankService.findById(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/bank")
    public ResponseEntity<JsonResult> post(@RequestBody Bank bank, @RequestParam("com-id") int comId) {
        try {
            return companyService.findById(comId)
                    .map(company -> {
                        bank.setCompany(company);
                        bank.setDeleted(false);
                        try {
                            return JsonResult.uploaded(bankService.save(bank));
                        } catch (Exception e) {
                            return JsonResult.error(e);
                        }
                    }).orElse(JsonResult.badRequest("company is not exist"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/bank")
    public ResponseEntity<JsonResult> put(@RequestBody Bank bank) {
        try {
            return JsonResult.updated(bankService.save(bank));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/banks")
    public ResponseEntity<JsonResult> put(@RequestBody List<Bank> banks) {
        try {
            return JsonResult.updated(bankService.saveAll(banks));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id){
        try {
            bankService.deleted(id);
            return JsonResult.deleted();
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
