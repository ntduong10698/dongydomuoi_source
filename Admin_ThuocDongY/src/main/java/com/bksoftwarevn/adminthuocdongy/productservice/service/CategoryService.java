package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Category;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.CategoryHasProduct;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.CategoryJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.CateJson;

import java.util.List;
import java.util.Set;

public interface CategoryService extends BaseService<Category>{

    Set<CategoryJson> findByProductType(int productTypeId) throws Exception;

    List<CategoryJson> findByParent(int parentId) throws Exception;

    List<CategoryHasProduct> findByProduct(int id);

    void addProduct(CategoryHasProduct chp);

    void addProducts(List<CategoryHasProduct> chps);

    void alterByProduct(int productId, List<CateJson> cateJsons);

    boolean addChildToParent(int parentId, Category child);
}
