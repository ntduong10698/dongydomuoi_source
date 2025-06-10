package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.HistoryChangeSitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/public/history-change-site-maps")
public class HistoryChangeSiteMapPublicController {

    @Autowired
    private HistoryChangeSitemapService historyChangeSitemapService;

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByCompanyId(@PathVariable(name = "id") int companyId,
                                                      @RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            Pageable pageRequest = PageRequest.of(page - 1, size);
            return JsonResult.success(new PageJson<>(historyChangeSitemapService.findByCompanyId(companyId,pageRequest)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
