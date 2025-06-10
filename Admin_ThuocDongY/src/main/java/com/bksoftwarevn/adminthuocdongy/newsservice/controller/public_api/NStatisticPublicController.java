package com.bksoftwarevn.adminthuocdongy.newsservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NStatisticService;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/news/v1/public/statistic")
public class NStatisticPublicController {

    private final NStatisticService NStatisticService;

    private final NewsService newsService;

    public NStatisticPublicController(NStatisticService NStatisticService, NewsService newsService) {
        this.NStatisticService = NStatisticService;
        this.newsService = newsService;
    }

    @PutMapping("/news/{id}/view")
    public ResponseEntity<JsonResult> increaseView(@PathVariable("id") int id){
        try {
            newsService.increaseView(id);
            NStatisticService.increaseView(id);
            return JsonResult.updated("increased");
        }catch (Exception e){
            return JsonResult.error(e);
        }
    }
}
