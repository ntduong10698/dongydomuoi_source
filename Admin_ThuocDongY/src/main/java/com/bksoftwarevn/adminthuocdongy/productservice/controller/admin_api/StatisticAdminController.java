package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/admin/statistic")
public class StatisticAdminController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/product/{id}")
    public ResponseEntity<JsonResult> statisticByProduct(@PathVariable(name = "id") int id,
                                                         @RequestParam(name = "from") String from,
                                                         @RequestParam(name = "to") String to) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date dateFrom = format.parse(from);
            Date dateTo = format.parse(to);
            return JsonResult.success(statisticService.findByProduct(id, dateFrom, dateTo));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
