package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.config.StatusConfig;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.DanhMucAlias;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.UrlAlias;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.UserJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.payload.UrlAliasPayLoad;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.ConfigSitemapService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.DanhMucAliasService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.UrlAliasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/url-alias")
public class UrlAliasPrivateController {

    @Autowired
    private UrlAliasService urlAliasService;

    @Autowired
    private DanhMucAliasService danhMucAliasService;

    @Autowired
    private ConfigSitemapService configSitemapService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody UrlAlias urlAlias,
                                             @RequestParam(name = "danh-muc-alias-id", required = true) int danhMucAliasId) {
        try {
            UrlAlias newUrlAlias = new UrlAlias();
            String alias = urlAlias.getAlias();
            //
            DanhMucAlias danhMucAlias = danhMucAliasService.findById(danhMucAliasId); //call service
            if (danhMucAlias != null) {
                newUrlAlias.setAlias(alias);
                newUrlAlias.setUrl(urlAlias.getUrl());
                newUrlAlias.setDanhMucAlias(danhMucAlias);
                newUrlAlias.setDateChange(new Date());
                newUrlAlias.setDeleted(false);
                try {
                    configSitemapService.changeStatus(danhMucAlias.getConfigSitemap().getCompanyId(), StatusConfig.CONFIG_SITEMAP_NO_UPDATED);
                    return JsonResult.uploaded(urlAliasService.save(newUrlAlias));
                } catch (Exception e) {
                    e.printStackTrace();
                    return JsonResult.badRequest("uploaded fail");
                }
            } else {
                return JsonResult.badRequest("config sitemap for company is not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody UrlAliasPayLoad urlAliasPayLoad, HttpServletRequest request) {
        try {
            UserJson u = (UserJson) request.getAttribute("user");
            String alias = urlAliasPayLoad.getAlias();
            String newAlias = urlAliasPayLoad.getNewAlias();
            int companyId = u.getComId();
            List<UrlAlias> urlAliasListNew = urlAliasService.findByCompanyIdAndAlias(companyId, newAlias);
            List<UrlAlias> urlAliasList = urlAliasService.findByCompanyIdAndAlias(companyId, alias);
            if (urlAliasListNew.isEmpty() && !urlAliasList.isEmpty()) {
                try {
                    configSitemapService.changeStatus(companyId, StatusConfig.CONFIG_SITEMAP_NO_UPDATED);
                    UrlAlias urlAlias = urlAliasList.get(0);
                    urlAlias.setDateChange(new Date());
                    urlAlias.setAlias(newAlias);
                    urlAliasService.save(urlAlias);
                    return JsonResult.updated(urlAlias);
                } catch (Exception e) {
                    e.printStackTrace();
                    return JsonResult.badRequest("updated fail");
                }
            } else {
                return JsonResult.badRequest("alias is exist");
            }
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (urlAliasService.deletedById(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exit");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/alias")
    public ResponseEntity<JsonResult> deleteByAlias(@RequestParam(name = "alias", required = true) String alias,
                                                    @RequestParam(name = "company-id", required = true) int companyId) {
        try {
            if (urlAliasService.deleteByAlias(alias, companyId))
                return JsonResult.deleted();
            return JsonResult.badRequest("delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}