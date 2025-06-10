package com.bksoftwarevn.adminthuocdongy.newsservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.News;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/news/v1/public/newses")
public class NewsPublicController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return newsService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter")
    @ApiOperation(value = "1: bai viet, 2: su kien, 3: trai nghiem, category = 0 if find by company", response = PageJson.class)
    public ResponseEntity<JsonResult> findByComId(@RequestParam(name = "category", defaultValue = "0") int cateId,
                                                  @RequestParam(name = "company", defaultValue = "0") int comId,
                                                  @RequestParam(name = "type", defaultValue = "0") int type,
                                                  @RequestParam(name = "name", defaultValue = "", required = false) String name,
                                                  @RequestParam(name = "tag", defaultValue = "", required = false) String tag,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "6") int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<News> newsPage = newsService.filter(cateId, comId, type, name, tag, pageable);
            newsPage.getContent().forEach(news -> {
                news.setContent(null);
            });
            return JsonResult.success(new PageJson<>(newsPage));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter2")
    @ApiOperation(value = "sortType: creatTime, view", response = PageJson.class)
    public ResponseEntity<JsonResult> filter2(@RequestParam(name = "company", defaultValue = "0") int comId,
                                              @RequestParam(name = "sort", defaultValue = "creatTime") String sortType,
                                              Pageable pageable) {
        try {
            Page<News> newsPage = newsService.filter2(comId, sortType, pageable);
            newsPage.getContent().forEach(news -> {
                news.setCategory(null);
                news.setContent(null);
            });
            return JsonResult.success(new PageJson<>(newsPage));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter-event")
    @ApiOperation(value = "1: bai viet, 2: su kien, 3: trai nghiem, category = 0 if find by company", response = PageJson.class)
    public ResponseEntity<JsonResult> findByComId(@RequestParam(name = "category", defaultValue = "0") int cateId,
                                                  @RequestParam(name = "company", defaultValue = "0") int comId,
                                                  @RequestParam(name = "name", defaultValue = "", required = false) String name,
                                                  @RequestParam(name = "tag", defaultValue = "", required = false) String tag,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "6") int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<News> newsPage = newsService.filterEvent(cateId, comId, name, tag, pageable);
            newsPage.getContent().forEach(news -> news.setContent(null));
            return JsonResult.success(newsPage);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/{id}/similar-news")
    public ResponseEntity<JsonResult> similar(@PathVariable("id") int id,
                                              @RequestParam(value = "size", defaultValue = "6") int size) {
        try {
            Set<News> newsSet = new HashSet<>(size);
            News news = newsService.findById(id).get();
            for (String tag : news.getTag().split("\\|")) {
                newsSet.addAll(newsService.findSimilar(tag, PageRequest.of(0, 6)).getContent());
            }
            return JsonResult.success(newsSet.stream().peek(n -> n.setContent(null)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
