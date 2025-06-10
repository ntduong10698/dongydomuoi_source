package com.bksoftwarevn.adminthuocdongy.newsservice.service;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NStatistic;

import java.util.Date;
import java.util.List;

public interface NStatisticService {

    List<NStatistic> findByNews(int newsId, Date from, Date to);

    void increaseView(int newsId);
}
