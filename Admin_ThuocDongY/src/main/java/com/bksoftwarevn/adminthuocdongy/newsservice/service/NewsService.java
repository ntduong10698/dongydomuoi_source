package com.bksoftwarevn.adminthuocdongy.newsservice.service;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.News;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.NewsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface NewsService extends BaseService<News> {

    Page<News> filter(int cateId, int comId, int type, String name, String tag, Pageable pageable);

    Page<News> findSimilar(String tag, Pageable pageable);

    Page<News> filterEvent(int cateId, int comId, String name, String tag, Pageable pageable);

    Page<News> filter2(int comId, String sort, Pageable pageable);

    List<NewsView> statistic(int comId, Date from, Date to);

    void increaseView(int id);

    int countByCate(int cateId);
}
