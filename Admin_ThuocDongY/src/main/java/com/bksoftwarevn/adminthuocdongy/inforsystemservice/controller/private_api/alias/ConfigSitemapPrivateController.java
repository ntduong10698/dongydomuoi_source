package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.config.StatusConfig;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.ConfigSitemap;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.ConfigSitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/private/config-site-map")
public class ConfigSitemapPrivateController {

    @Autowired
    private ConfigSitemapService configSitemapService;


    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody ConfigSitemap configSitemap) {
        try {
            String url = configSitemap.getUrlDirectory();
            List<ConfigSitemap> configSitemapList = configSitemapService.findByUrlDirectory(url);
            if(configSitemapList == null || configSitemapList.isEmpty()) {
                configSitemap.setStatus(StatusConfig.CONFIG_SITEMAP_NO_UPDATED);
                configSitemap.setDeleted(false);
                try {
                    return JsonResult.uploaded(configSitemapService.save(configSitemap));
                } catch (Exception e) {
                    return JsonResult.badRequest("upload fail");
                }
            } else {
                return JsonResult.badRequest("url is exist");
            }
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody ConfigSitemap configSitemap) {
        try {
            String url = configSitemap.getUrlDirectory();
            List<ConfigSitemap> configSitemapList = configSitemapService.findByUrlDirectory(url);
            if(configSitemapList == null || configSitemapList.isEmpty()) {
                configSitemap.setStatus(StatusConfig.CONFIG_SITEMAP_NO_UPDATED);
                try {
                    return JsonResult.updated(configSitemapService.save(configSitemap));
                } catch (Exception e) {
                    return JsonResult.badRequest("upload fail");
                }
            } else {
                return JsonResult.badRequest("url is exist");
            }
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (configSitemapService.deleted(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exit");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
