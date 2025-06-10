package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Information;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class InfoAdminController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/info/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return infoService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }


    @PostMapping("/info")
    public ResponseEntity<JsonResult> addContact(@RequestBody Information information,
                                                @RequestParam(value = "company-id",required = false)Integer congTyId){
        try {
            return companyService.findById(congTyId)
                    .map(company -> {
                        information.setDeleted(false);
                        information.setCompany(company);
                        return infoService.save(information)
                                .map(JsonResult::uploaded)
                                .orElse(JsonResult.badRequest("Contact"));
                    })
                    .orElse(JsonResult.badRequest("Company"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/info")
    public ResponseEntity<JsonResult> update(@RequestBody Information information){
        try {
            return infoService.save(information)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("Branch"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/infos")
    public ResponseEntity<JsonResult> update(@RequestBody List<Information> information){
        try {
            return JsonResult.success(infoService.saveAll(information));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/info/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (infoService.deleted(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

}
