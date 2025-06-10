package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.UserJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/admin/company")
public class CompanyAdminController {

    @Autowired
    private CompanyService companyService;

//    @PostMapping
//    public ResponseEntity<JsonResult> upload(@RequestBody Company company, HttpServletRequest request) {
//        try {
//            UserJson userJson = (UserJson) request.getAttribute("user");
//            company.setDeleted(false);
//            return companyService.save(company)
//                    .map(JsonResult::uploaded)
//                    .orElse(JsonResult.badRequest("data is invalid"));
//        } catch (Exception ex) {
//            return JsonResult.error(ex);
//        }
//    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Company company, HttpServletRequest request) {
        try {
            UserJson userJson = (UserJson) request.getAttribute("user");
            if (userJson.getComId() == company.getId())
                return companyService.save(company)
                        .map(JsonResult::updated)
                        .orElse(JsonResult.badRequest("data is invalid"));
            else {
                return ResponseEntity.status(401).body(new JsonResult(false,null,"no author"));
            }
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id) {
//        try {
//            if (companyService.deleted(id))
//                return JsonResult.deleted();
//            return JsonResult.badRequest("id is not exist");
//        } catch (Exception e) {
//            return JsonResult.error(e);
//        }
//    }
}
