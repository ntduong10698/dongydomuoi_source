package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.ConfigSitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/config-site-maps")
public class ConfigSitemapPublicController {

    @Autowired
    private ConfigSitemapService configSitemapService;

    @GetMapping
    public ResponseEntity<JsonResult> findAll() {
        try {
            return JsonResult.success(configSitemapService.findAll());
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<JsonResult> findByCompanyId(@PathVariable(name = "id") int companyId) {
        try {
            return JsonResult.success(configSitemapService.findByCompanyId(companyId));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<JsonResult> filter(@RequestParam(name = "url_directory", defaultValue = "", required = false) String urlDirectory,
                                             @RequestParam(name = "company_id", defaultValue = "0") int companyId,
                                             @RequestParam(name = "status", defaultValue = "-1") int status) {
        try {
            return JsonResult.success(configSitemapService.filter(urlDirectory, companyId, status));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
