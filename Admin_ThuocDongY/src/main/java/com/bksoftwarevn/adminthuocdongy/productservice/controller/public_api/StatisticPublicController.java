package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductStatistic;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.StatisticJson;
import com.bksoftwarevn.adminthuocdongy.productservice.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/statistic")
public class StatisticPublicController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/product/{id}")
    public ResponseEntity<JsonResult> statistic(@PathVariable(name = "id") int proId) {
        try {
            ProductStatistic ps = statisticService.findTotal(proId).get();
            StatisticJson json = new StatisticJson(0, 0);
            json.setViewed(ps.getViewed());
            json.setSold(ps.getSold());
            return JsonResult.success(json);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/increase-view/product/{id}")
    public ResponseEntity<JsonResult> increaseView(@PathVariable(name = "id") int id) {
        try {
            statisticService.increaseViewed(id);
            return JsonResult.success("viewed");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
