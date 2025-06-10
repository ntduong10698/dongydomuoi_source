package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.alias;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.alias.TanSuatThayDoiAlias;

import java.util.List;

public interface TanSuatThayDoiAliasService {

    TanSuatThayDoiAlias findById(int id);

    List<TanSuatThayDoiAlias> findAll();

    int delete(int id);
}
