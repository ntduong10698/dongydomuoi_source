package com.bksoftwarevn.adminthuocdongy.newsservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.newsservice.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/news/v1/admin/statistic")
public class NStatisticAdminController {

    private final NewsService newsService;

    public NStatisticAdminController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/company/{id}")
    public ResponseEntity<JsonResult> statistic(@PathVariable("id") int comId,
                                                @RequestParam("from") String from,
                                                @RequestParam("to") String to) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return JsonResult.success(newsService.statistic(comId, formatter.parse(from), formatter.parse(to)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
