package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.ConfigSitemap;

import java.util.List;

public interface ConfigSitemapService {

    ConfigSitemap findByCompanyId(int id);

    List<ConfigSitemap> findAll();

    boolean deleted(int id);

    List<ConfigSitemap> filter(String urlDirectory, int companyId, int status);

    ConfigSitemap save(ConfigSitemap configSitemap);

    boolean changeStatus(int compnayId, int status);

    List<ConfigSitemap> findByUrlDirectory(String url);
}
