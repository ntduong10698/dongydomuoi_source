package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.DanhMucAliasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/danh-muc-alias")
public class DanhMucAliasController {

    @Autowired
    private DanhMucAliasService danhMucAliasService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(danhMucAliasService.findById(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping
    public ResponseEntity<JsonResult> findAll() {
        try {
            return JsonResult.success(danhMucAliasService.findAll());
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("company/{companyId}")
    public ResponseEntity<JsonResult> findByCompanyId(@PathVariable( name = "companyId") int companyId) {
        try {
            return JsonResult.success(danhMucAliasService.findByCompanyId(companyId));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
