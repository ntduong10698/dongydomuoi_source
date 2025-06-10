package com.bksoftwarevn.adminthuocdongy.productservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductStatistic;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.ProductRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.repository.StatisticRepo;
import com.bksoftwarevn.adminthuocdongy.productservice.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepo repo;
    @Autowired
    private ProductRepo productRepo;

    private static final Logger logger = LoggerFactory.getLogger(StatisticServiceImpl.class);

    @Override
    public List<ProductStatistic> findByProduct(int productId, Date from, Date to) {
        try {
            return repo.findByProduct(productId, from, to);
        } catch (Exception ex) {
            logger.error("find statistic by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Optional<ProductStatistic> findTotal(int productId) {
        try {
            return repo.findTotal(productId);
        } catch (Exception ex) {
            logger.error("find statistic by product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void increaseViewed(int productId) {
        try {
            Date today = new Date();
            ProductStatistic ps = repo.findByDate(productId, today).orElse(null);
            if (ps == null) {
                ProductStatistic newPs = new ProductStatistic();
                newPs.setDate(today);
                newPs.setProduct(productRepo.findDataById(productId).orElse(null));
                newPs.setViewed(0);
                newPs.setSold(0);
                repo.save(newPs);
            }
            repo.increaseViewed(productId, today);
            repo.increaseViewedTotal(productId);
        } catch (Exception ex) {
            logger.error("increase view product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void increaseSold(int productId, int amount) {
        try {
            Date today = new Date();
            ProductStatistic ps = repo.findByDate(productId, today).orElse(null);
            if (ps == null) {
                ProductStatistic newPs = new ProductStatistic();
                newPs.setDate(today);
                newPs.setProduct(productRepo.findDataById(productId).orElse(null));
                newPs.setViewed(0);
                newPs.setSold(amount);
                repo.save(newPs);
            } else
                repo.increaseSold(productId, amount, today);

            repo.increaseSoldTotal(productId, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("increase sold product err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void createStatistic(int productId) {
        try {
            ProductStatistic newPs = new ProductStatistic();
            newPs.setProduct(productRepo.findDataById(productId).orElse(null));
            newPs.setViewed(0);
            newPs.setSold(0);
            newPs.setDate(null);
            repo.save(newPs);
        } catch (Exception ex) {
            logger.error("create product statistic err {0}", ex);
            throw ex;
        }
    }
}
