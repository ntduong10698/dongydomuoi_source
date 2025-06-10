package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductHasProperty;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductStatistic;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.DecreaseForm;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.PHPJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.ProductCount;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.ProductPropertyKey;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.*;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Integer, ProductRepo> implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private CateHasProductRepo cateHasProductRepo;

    @Autowired
    private ProductHasPropertyRepo phpRepo;

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private StatisticRepo statisticRepo;

    public ProductServiceImpl(ProductRepo repo) {
        super(repo, "Product");
    }

    @Override
    public Page<Product> adminFilterDate(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable, boolean dateAsc) {
        try {
            if (dateAsc) {
                return cateHasProductRepo.adminFilterDateAsc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
            }
            else
                return cateHasProductRepo.adminFilterDateDesc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
        } catch (Exception ex) {
            logger.error("find product admin filter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Product> adminFilterViewed(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable, boolean viewedAsc) {
        try {
            if (viewedAsc)
                return cateHasProductRepo.adminFilterViewAsc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
            else
                return cateHasProductRepo.adminFilterViewDesc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
        } catch (Exception ex) {
            logger.error("find product admin filter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Product> adminFilterSold(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable, boolean soldAsc) {
        try {
            if (soldAsc)
                return cateHasProductRepo.adminFilterSoldAsc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
            else
                return cateHasProductRepo.adminFilterSoldDesc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
        } catch (Exception ex) {
            logger.error("find product admin filter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Product> adminFilterCost(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable, boolean costAsc) {
        try {
            if (costAsc)
                return cateHasProductRepo.adminFilterCostAsc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
            else
                return cateHasProductRepo.adminFilterCostDesc(productTypeId, enableCate, cateIds, brandId, text, status,comId, pageable);
        } catch (Exception ex) {
            logger.error("find product admin filter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Product> filterService(int productTypeId, int cateId, String text, Pageable pageable) {
        try {
            return cateHasProductRepo.filterService(productTypeId, cateId, text, pageable);
        } catch (Exception ex) {
            logger.error("find service err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void decreaseByIds(List<DecreaseForm> forms) {
        try {
            for (DecreaseForm form : forms) {
                repo.decreaseById(form.getNumber(), form.getProductId());
            }
        } catch (Exception ex) {
            logger.error("decrease products err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Product> findByIds(List<Integer> ids) {
        try {
            return repo.findDataByIds(ids);
        } catch (Exception ex) {
            logger.error("find products by ids err {0}", ex);
            throw ex;
        }
    }

    @Override
    public int countByBrand(int brandId) {
        try {
            return repo.countByBrand(brandId);
        } catch (Exception ex) {
            logger.error("count product by brand err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Integer> findProductIdByCate(int cateId) {
        try {
            return cateHasProductRepo.findProductIdByCate(cateId);
        } catch (Exception ex) {
            logger.error("find product id by cate err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void deletes(List<Integer> ids) {
        try {
             repo.deleteDataById(ids);
        } catch (Exception ex) {
            logger.error("delete products by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void alterStatus(int status, List<Integer> ids) {
        try {
            repo.alterStatusById(status, ids);
        } catch (Exception ex) {
            logger.error("alter status products by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ProductHasProperty> findPHPByProduct(int productId) {
        try {
            return phpRepo.findByProduct(productId);
        } catch (Exception ex) {
            logger.error("find productHasProperty by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void addProperty(int productId, List<PHPJson> phpJsons) {
        try {
            phpJsons.forEach(json -> {
                ProductHasProperty temp = new ProductHasProperty();
                temp.setDeleted(false);
                temp.setValue(json.getValue());
                temp.setId(new ProductPropertyKey(productId, json.getPropertyId()));
                temp.setProductProperty(propertyRepo.findDataById(json.getPropertyId()).orElse(null));
                phpRepo.save(temp);
            });
        } catch (Exception ex) {
            logger.error("add property to product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void updateProperty(List<ProductHasProperty> phps) {
        try {
            phpRepo.saveAll(phps);
        } catch (Exception ex) {
            logger.error("add property to product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ProductCount> sortByView(int productTypeId, Date from, Date to) {
        try {
            List<ProductStatistic> productStatistics = statisticRepo.findByProductType(productTypeId, from, to);
            return null;
        } catch (Exception ex) {
            logger.error("add property to product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<ProductCount> sortBySold(int productTypeId, Date from, Date to) {
        return null;
    }

}
