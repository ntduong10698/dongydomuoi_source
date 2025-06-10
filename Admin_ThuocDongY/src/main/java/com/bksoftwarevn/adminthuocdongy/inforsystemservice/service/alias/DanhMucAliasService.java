package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.DanhMucAlias;

import java.util.List;

public interface DanhMucAliasService {

    DanhMucAlias findById(int id);

    List<DanhMucAlias> findAll();

    int delete(int id);

    List<DanhMucAlias> findByCompanyId(int companyId);
}
