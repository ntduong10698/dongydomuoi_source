package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Part;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/part")
public class PartAdminController {

    @Autowired
    private PartService partService;

    @Autowired
    private CompanyService companyService;


    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return partService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> addPart(@RequestBody Part part,
                                                @RequestParam(value = "company-id",required = false)Integer id){
        try {
            return companyService.findById(id)
                    .map(company -> {
                        part.setCompany(company);
                        return partService.save(part)
                                .map(JsonResult::uploaded)
                                .orElse(JsonResult.badRequest("Part"));
                    })
                    .orElse(JsonResult.badRequest("Company"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Part part){
        try {
            return partService.save(part)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("Part"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/{id}/video/{video}")
    public ResponseEntity<JsonResult> updateVideo(@PathVariable("id") int id, @PathVariable("video") boolean video){
        try {
            partService.setVideo(id, video);
            return JsonResult.success("ok");
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (partService.deleted(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable(name = "id") int id,
                                                  @RequestParam(value = "code", required = false, defaultValue = "") String code){
        try {
            if (code == null) code = "";
            return JsonResult.success(partService.findByCompany(id, code));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }


}
