package com.bksoftwarevn.adminthuocdongy.newsservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.NStatistic;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.News;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.NewsView;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NStatisticRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.repository.NewsRepo;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News, Integer, NewsRepo> implements NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    private final NStatisticRepo NStatisticRepo;

    public NewsServiceImpl(NewsRepo repo, NStatisticRepo NStatisticRepo) {
        super(repo, "News");
        this.NStatisticRepo = NStatisticRepo;
    }

    @Override
    public Page<News> filter(int cateId, int comId, int type, String name, String tag, Pageable pageable) {
        try {
            return repo.filter(cateId, comId, type, name, tag, pageable);
        } catch (Exception ex) {
            logger.error("find news by cate err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<News> findSimilar(String tag, Pageable pageable) {
        try {
            return repo.findSimilar(tag, pageable);
        } catch (Exception ex) {
            logger.error("find news by similars err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<News> filterEvent(int cateId, int comId, String name, String tag, Pageable pageable) {
        try {
            return repo.filterEvent(cateId, comId, name, tag, pageable);
        } catch (Exception ex) {
            logger.error("find events by cate err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<News> filter2(int comId, String sort, Pageable pageable) {
        try {
            Pageable pageable2 = PageRequest.of(pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, sort));
            return repo.filter2(comId, pageable2);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<NewsView> statistic(int comId, Date from, Date to) {
        try {
            List<News> newsList = repo.findByCompany(comId);
            List<NStatistic> NStatistics = NStatisticRepo.findByMultiNews(newsList.stream().map(News::getId).collect(Collectors.toList()), from, to);
            List<NewsView> newsViews = newsList.stream().map(news -> {
                NewsView view = new NewsView();
                view.setId(news.getId());
                view.setName(news.getName());
                List<NStatistic> thisNStatistics = NStatistics.stream().filter(s -> s.getNews().getId() == news.getId()).collect(Collectors.toList());
                view.setView(thisNStatistics.stream().mapToInt(NStatistic::getView).sum());
                return view;
            }).sorted((n1, n2) -> n2.getView() - n1.getView()).collect(Collectors.toList());
            return newsViews;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void increaseView(int id) {
        repo.increaseView(id);
    }

    @Override
    public int countByCate(int cateId) {
        try {
            return repo.countByCate(cateId);
        } catch (Exception ex) {
            logger.error("count news by cate err {0}", ex);
            throw ex;
        }
    }
}
