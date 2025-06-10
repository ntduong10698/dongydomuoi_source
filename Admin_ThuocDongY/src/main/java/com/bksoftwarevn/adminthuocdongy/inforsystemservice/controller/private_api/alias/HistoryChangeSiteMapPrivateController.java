package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.ConfigSitemap;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.HistoryChangeSitemap;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.ConfigSitemapService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.HistoryChangeSitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1/private/history-change-site-map")
public class HistoryChangeSiteMapPrivateController {

    @Autowired
    private HistoryChangeSitemapService historyChangeSitemapService;

    @Autowired
    private ConfigSitemapService configSitemapService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestParam(name = "company_id", required = true) int companyId) {
        try {
            ConfigSitemap configSitemap = configSitemapService.findByCompanyId(companyId);
            if(configSitemap != null) {
                HistoryChangeSitemap historyChangeSitemap = new HistoryChangeSitemap();
                historyChangeSitemap.setConfigSitemap(configSitemap);
                historyChangeSitemap.setTime(new Date());
                try {
                    return JsonResult.uploaded(historyChangeSitemapService.save(historyChangeSitemap));
                } catch (Exception ex) {
                    return JsonResult.badRequest("uploaded fail");
                }
            } else {
                return JsonResult.badRequest("config sitemap for company is not exist");
            }
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
