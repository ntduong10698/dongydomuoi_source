package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.config.StatusConfig;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.*;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias.*;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/site-map")
public class SiteMapController {

    @Value("${xmlns}")
    private String xmlns;

    @Autowired
    private DanhMucAliasService danhMucAliasService;

    @Autowired
    private ObjectToXmlService objectToXmlService;

    @Autowired
    private UrlAliasService urlAliasService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ConfigSitemapService configSitemapService;

    @Autowired
    private HistoryChangeSitemapService historyChangeSitemapService;

    @PutMapping("/{companyId}")
    private ResponseEntity<JsonResult> updateSiteMap(@PathVariable(name = "companyId") int id) {
        try {
            List<DanhMucAlias> danhMucAliasList = danhMucAliasService.findByCompanyId(id);
            if(danhMucAliasList != null && !danhMucAliasList.isEmpty()) {
                ConfigSitemap configSitemap = danhMucAliasList.get(0).getConfigSitemap();
                int status = configSitemap.getStatus();
                switch (status) {
                    case StatusConfig.CONFIG_SITEMAP_NO_UPDATED:
                        List<String> listRs = new ArrayList<>();
                        listRs.add(writeDanhMucAlias(danhMucAliasList));
                        for (DanhMucAlias d: danhMucAliasList) {
                            listRs.add(writeAlias(d));
                        }
                        configSitemapService.changeStatus(id, StatusConfig.CONFIG_SITEMAP_UPDATED);
                        HistoryChangeSitemap historyChangeSitemap = new HistoryChangeSitemap();
                        historyChangeSitemap.setConfigSitemap(configSitemap);
                        historyChangeSitemap.setTime(new Date());
                        historyChangeSitemapService.save(historyChangeSitemap);
                        return JsonResult.success(listRs);
                    case StatusConfig.CONFIG_SITEMAP_UPDATED:
                    default:
                        return JsonResult.success("Sitemap đã được cập nhật thông tin mới nhất.");
                }
            } else {
                return JsonResult.badRequest("company id not found");
            }
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    private String writeDanhMucAlias(List<DanhMucAlias> danhMucAliasList) {
        String rs = null;
        List<SiteMap> siteMapList = new ArrayList<>();
        Company company = companyService.findById(danhMucAliasList.get(0).getConfigSitemap().getCompanyId()).orElse(null);
        if(company != null) {
            String urlCompany = company.getWebsite().trim();
            for (DanhMucAlias danhMucAlias: danhMucAliasList) {
                siteMapList.add(new SiteMap(urlCompany.concat("sitemap_"+danhMucAlias.getName()+".xml")));
            }
            SiteMapIndex siteMapIndex = new SiteMapIndex(siteMapList);
            try {
                JAXBContext contextObj = JAXBContext.newInstance(SiteMapIndex.class);
//            objectToXmlService.ObjectToXml(contextObj, siteMapIndex, danhMucAliasList.get(0).getConfigSitemap().getUrlDirectory().concat("sitemap.xml"));
                boolean check = objectToXmlService.ObjectToXml(contextObj, siteMapIndex, danhMucAliasList.get(0).getConfigSitemap().getUrlDirectory().concat("sitemap.xml"));
                if(check) rs = urlCompany.concat("sitemap.xml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    private String writeAlias(DanhMucAlias danhMucAlias) {
        String rs = null;
        List<UrlAlias> urlAliasList = urlAliasService.findByDanhMucAlias(danhMucAlias.getId());
        if(urlAliasList != null && !urlAliasList.isEmpty()) {
            UrlSet urlSet = new UrlSet();
            Company company = companyService.findById(danhMucAlias.getConfigSitemap().getCompanyId()).orElse(null);
            if(company != null)  {
                String urlCompany = company.getWebsite().trim();
                String lastmod = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String changefreq = danhMucAlias.getTanSuatThayDoiAlias().getTanSuatThayDoi();
                Double priority = danhMucAlias.getDoQuanTrong();
                List<UrlSiteMap> urlSiteMapList = new ArrayList<>();
                for (UrlAlias u: urlAliasList) {
                    urlSiteMapList.add(new UrlSiteMap(urlCompany.concat(u.getAlias()), lastmod, changefreq, priority));
                }
                urlSet.setUrlset(urlSiteMapList);
                try {
                    JAXBContext contextObj = JAXBContext.newInstance(UrlSet.class);
//            objectToXmlService.ObjectToXml(contextObj, siteMapIndex, danhMucAliasList.get(0).getConfigSitemap().getUrlDirectory().concat("sitemap.xml"));
                    String nameSiteMap = "sitemap_".concat(danhMucAlias.getName()).concat(".xml");
                    boolean check = objectToXmlService.ObjectToXml(contextObj, urlSet, danhMucAlias.getConfigSitemap().getUrlDirectory().concat(nameSiteMap));
                    if(check) rs = urlCompany.concat(nameSiteMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return rs;
    }
}

