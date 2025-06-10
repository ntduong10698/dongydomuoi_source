package com.bksoftwarevn.adminthuocdongy.productservice.service;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductHasProperty;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.DecreaseForm;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.PHPJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.ProductCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ProductService extends BaseService<Product> {

    Page<Product> adminFilterDate(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable, boolean dateAsc);

    Page<Product> adminFilterViewed(int productTypeId, boolean enableCate, List<Integer> cateIds,int brandId,  String text, int status,int comId, Pageable pageable, boolean viewedAsc);

    Page<Product> adminFilterSold(int productTypeId, boolean enableCate, List<Integer> cateIds,int brandId,  String text, int status,int comId, Pageable pageable, boolean soldAsc);

    Page<Product> adminFilterCost(int productTypeId, boolean enableCate, List<Integer> cateIds,int brandId,  String text, int status,int comId, Pageable pageable, boolean costAsc);

    Page<Product> filterService(int productTypeId, int cateId, String text, Pageable pageable);

    void decreaseByIds(List<DecreaseForm> forms);

    List<Product> findByIds(List<Integer> ids);

    int countByBrand(int brandId);

    List<Integer> findProductIdByCate(int cateId);

    void deletes(List<Integer> ids);

    void alterStatus(int status, List<Integer> ids);

    List<ProductHasProperty> findPHPByProduct(int productId);

    void addProperty(int productId, List<PHPJson> phpJsons);

    void updateProperty( List<ProductHasProperty> phps);

    List<ProductCount> sortByView(int productTypeId, Date from, Date to);

    List<ProductCount> sortBySold(int productTypeId, Date from, Date to);

}
