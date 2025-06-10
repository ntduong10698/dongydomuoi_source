package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.PartDetail;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartDetailService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/part-detail")
public class PartDetailController {

    @Autowired
    private PartService partService;

    @Autowired
    private PartDetailService partDetailService;


    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return partDetailService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> addPart(@RequestBody PartDetail partDetail,
                                              @RequestParam(value = "part-id", required = false) Integer id) {
        try {
            return partService.findById(id)
                    .map(part -> {
                        partDetail.setPart(part);
                        return partDetailService.save(partDetail)
                                .map(JsonResult::uploaded)
                                .orElse(JsonResult.badRequest("PartDetail"));
                    })
                    .orElse(JsonResult.badRequest("Part"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody PartDetail partDetail) {
        try {
            return partDetailService.save(partDetail)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("PartDetail"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (partDetailService.deleted(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/part/{id}")
    public ResponseEntity<JsonResult> findByPartId(@PathVariable(name = "id") int id) {
        try {

            return JsonResult.success(partDetailService.findByPartId(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
