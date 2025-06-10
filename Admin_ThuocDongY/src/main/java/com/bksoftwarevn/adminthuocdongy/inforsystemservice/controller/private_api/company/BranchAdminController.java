package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Branch;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BranchService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/admin/branch")
public class BranchAdminController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<JsonResult> addBranch(@RequestBody Branch branch,
                                                @RequestParam(value = "company-id", required = false) Integer congTyId,
                                                HttpServletRequest request) {
        try {

            return companyService.findById(congTyId)
                    .map(company -> {
                        branch.setDeleted(false);
                        branch.setCompany(company);
                        return branchService.save(branch)
                                .map(JsonResult::uploaded)
                                .orElse(JsonResult.badRequest("Branch"));
                    })
                    .orElse(JsonResult.badRequest("Company"));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Branch branch, HttpServletRequest request) {
        try {
            return branchService.save(branch)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("Branch"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int branchId, HttpServletRequest request) {
        try {
            if (branchService.deleted(branchId))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

}
