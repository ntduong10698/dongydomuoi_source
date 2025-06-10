package com.bksoftwarevn.adminthuocdongy.newsservice.service;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NCategory;

import java.util.List;

public interface NCategoryService extends BaseService<NCategory>{

    List<NCategory> findByCompany(int comId);
}
