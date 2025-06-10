package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.UrlAlias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UrlAliasService {

    List<UrlAlias> findByCompanyIdAndAlias(int id, String alias);

    boolean deletedById(int id);

    boolean deleteByAlias(String alias, int comId);

    boolean checkAlias(String alias, int comId);

    Page<UrlAlias> filter(String alias, String url, int companyId, Pageable pageable);

    UrlAlias save(UrlAlias urlAlias);

    UrlAlias findById(int id);

    List<UrlAlias> findByDanhMucAlias(int id);
}
