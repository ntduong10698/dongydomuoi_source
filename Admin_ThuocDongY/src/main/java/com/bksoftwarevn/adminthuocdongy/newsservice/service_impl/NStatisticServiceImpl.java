package com.bksoftwarevn.adminthuocdongy.newsservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NStatistic;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NStatisticRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NewsRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NStatisticServiceImpl implements NStatisticService {

    @Autowired
    private NStatisticRepo NStatisticRepo;

    @Autowired
    private NewsRepo newsRepo;

    @Override
    public List<NStatistic> findByNews(int newsId, Date from, Date to) {
        return NStatisticRepo.findByNews(newsId, from, to);

    }

    @Override
    public void increaseView(int newsId) {
        Date today = new Date();
        Optional<NStatistic> statistic = NStatisticRepo.findByDate(today);
        if (!statistic.isPresent()) {
            NStatistic todayNStatistic = new NStatistic();
            todayNStatistic.setNews(newsRepo.findDataById(newsId).get());
            todayNStatistic.setView(1);
            todayNStatistic.setDate(today);
            NStatisticRepo.save(todayNStatistic);
        } else
            NStatisticRepo.increaseView(newsId, today);
    }
}
