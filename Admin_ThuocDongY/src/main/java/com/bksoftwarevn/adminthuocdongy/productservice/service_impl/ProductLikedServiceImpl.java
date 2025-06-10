package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductLiked;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.ProductLikeKey;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.ProductLikedRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.ProductLikedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLikedServiceImpl implements ProductLikedService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductLikedRepo repo;

    @Override
    public List<Product> findByUser(int userId) {
        try {
            return repo.findByUser(userId);
        } catch (Exception ex) {
            logger.error("find product liked by user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void save(int userId, List<Product> products) {
        try {
            products.forEach(product -> {
                ProductLiked liked = new ProductLiked();
                liked.setDeleted(false);
                liked.setId(new ProductLikeKey(product.getId(), userId));
                liked.setProduct(product);
                repo.save(liked);
            });
        } catch (Exception ex) {
            logger.error(" user likes products fail err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void delete(int userId, List<Integer> products) {
        try {
            products.forEach(product -> repo.delete(new ProductLikeKey(product, userId)));
        } catch (Exception ex) {
            logger.error(" user unlikes products fail err {0}", ex);
            throw ex;
        }
    }
}
