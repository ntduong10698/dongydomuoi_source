package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Category;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.CategoryHasProduct;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.CategoryJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.CateJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.CategoryProductKey;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.CateHasProductRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.CategoryRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.ProductRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer, CategoryRepo> implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepo repo) {
        super(repo, "Category");
    }

    @Autowired
    private CateHasProductRepo cateHasProductRepo;

    @Autowired
    private ProductRepo productRepo;

    private List<CategoryJson> findAllChild(CategoryJson parent) {
        List<Category> categories = repo.findByParent(parent.getId());
        List<CategoryJson> jsons = new ArrayList<>();
        if (categories != null && !categories.isEmpty()) {
            categories.forEach(cate -> {
                CategoryJson json = new CategoryJson(cate);
                json.setLevel(parent.getLevel() + "." + cate.getStt());
                json.setChilds(findAllChild(json));
                jsons.add(json);
            });
        }
        return jsons;
    }

    @Override
    public Set<CategoryJson> findByProductType(int productTypeId) throws Exception {
        try {
            Set<CategoryJson> datas = new LinkedHashSet<>();
            List<Category> roots = repo.findByProductType(productTypeId);
            roots.forEach(root -> {
                CategoryJson jsonRoot = new CategoryJson(root);
                datas.add(jsonRoot);
                jsonRoot.setLevel(root.getStt() + "");
                jsonRoot.setChilds(findAllChild(jsonRoot));
            });
            return datas;
        } catch (Exception ex) {
            logger.error("find category by brand err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<CategoryJson> findByParent(int parentId) throws Exception {
        try {
            List<Category> childs = repo.findByParent(parentId);
            return childs.stream().map(cate -> {
                CategoryJson json = new CategoryJson(cate);
                json.setLevel(cate.getStt()+"");
                return json;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error("find category by parent err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<CategoryHasProduct> findByProduct(int id) {
        try {
            return cateHasProductRepo.findByProduct(id);
        } catch (Exception ex) {
            logger.error("find category by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void addProduct(CategoryHasProduct chp) {
        try {
            cateHasProductRepo.save(chp);
        } catch (Exception ex) {
            logger.error("add product to category err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void addProducts(List<CategoryHasProduct> chps) {
        try {
            cateHasProductRepo.saveAll(chps);
        } catch (Exception ex) {
            logger.error("add products to category err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void alterByProduct(int productId, List<CateJson> cateJsons) {
        try {
            cateJsons.forEach(cateJson -> {
                if (cateJson.isDeleted())
                    cateHasProductRepo.deleteById(new CategoryProductKey(productId, cateJson.getCateId()));
                if (cateJson.isNewCate()){
                    CategoryHasProduct chp = new CategoryHasProduct();
                    Category category = repo.findDataById(cateJson.getCateId()).get();
                    Product product = productRepo.findDataById(productId).get();
                    chp.setProduct(product);
                    chp.setCategory(category);
                    chp.setId(new CategoryProductKey(productId, cateJson.getCateId()));
                    cateHasProductRepo.save(chp);
                }
            });
        } catch (Exception ex) {
            logger.error("add products to category err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean addChildToParent(int parentId, Category child) {
        try {
            Optional<Category> cate = repo.findDataById(parentId);
            if (cate.isPresent()){
                Category parent = cate.get();
                parent.setSmallest(false);
                child.setProductType(parent.getProductType());
                repo.save(parent);
                return true;
            }
            return false;
        }catch (Exception ex){
            logger.error("add child to parent err {0}", ex);
            return false;
        }
    }
}
