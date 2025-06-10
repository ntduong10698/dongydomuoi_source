package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.UrlAliasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/url-alias")
public class UrlAliasPublicController {

    @Autowired
    private UrlAliasService urlAliasService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(urlAliasService.findById(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/danh-muc/{id}")
    public ResponseEntity<JsonResult> findByDanhMuc(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(urlAliasService.findByDanhMucAlias(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<JsonResult> filter(@RequestParam (name = "alias", defaultValue = "", required = false) String alias,
                                             @RequestParam (name = "url", defaultValue = "", required = false) String url,
                                             @RequestParam (name = "company-id", defaultValue = "0") int companyId,
                                             @RequestParam (name = "page", defaultValue = "1") int page,
                                             @RequestParam (name = "size", defaultValue = "10") int size
    ) {
        try {
            Pageable pageRequest = PageRequest.of(page - 1, size);
            return JsonResult.success(new PageJson<>(urlAliasService.filter(alias, url, companyId, pageRequest)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/alias/{alias}/company-id/{companyId}")
    public ResponseEntity<JsonResult> findByAliasAndCompanyId(@PathVariable(name = "companyId") int companyId,
                                                              @PathVariable(name = "alias") String alias) {
        try {
            return JsonResult.success(urlAliasService.findByCompanyIdAndAlias(companyId, alias));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/check-exist")
    public ResponseEntity<JsonResult> checkExist(@RequestParam (name = "alias", defaultValue = "", required = false) String alias,
                                                 @RequestParam (name = "company-id", defaultValue = "0") int companyId
    ){
        try {
            return JsonResult.success(urlAliasService.checkAlias(alias, companyId));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
