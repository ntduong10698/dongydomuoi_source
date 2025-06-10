package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.BillResponse;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/v1/admin/bills")
public class BillsAdmin {
    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            BillResponse billResponse = new BillResponse();
            billResponse.setBill(billService.findById(id).orElse(null));
            billResponse.setBillDetails(billDetailService.findByBillId(id));
            return JsonResult.success(billResponse);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/company/{comId}/uncheck")
    public ResponseEntity<JsonResult> countUncheck(@PathVariable(name = "comId") int id) {
        try {
            return JsonResult.success(billService.countUnchecked(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<JsonResult> adminFilter(@RequestParam(name = "start-date",required = false, defaultValue = "1970/01/01") String startDate,
                                                  @RequestParam(name = "end-date",required = false, defaultValue = "9999/12/31") String endDate,
                                                  @RequestParam(name = "status", defaultValue = "0") int statusId,
                                                  @RequestParam(name = "source", defaultValue = "0") int sourceId,
                                                  @RequestParam(name = "code",required = false, defaultValue = "") String code,
                                                  @RequestParam(name = "text",required = false, defaultValue = "") String text,
                                                  @RequestParam(name = "dateAsc", defaultValue = "true") boolean dateAsc,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date sDate = formatter.parse(startDate);
            Date eDate = formatter.parse(endDate);
            PageRequest request = PageRequest.of(page - 1, size);
            return JsonResult.success(new PageJson<>(billService.filter(sDate, eDate, 0, statusId, sourceId, code, text, request, dateAsc)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter/v2")
    public ResponseEntity<JsonResult> adminFilter2(@RequestParam(name = "start-date",required = false, defaultValue = "1970/01/01") String startDate,
                                                  @RequestParam(name = "end-date",required = false, defaultValue = "9999/12/31") String endDate,
                                                  @RequestParam(name = "status", defaultValue = "0") int statusId,
                                                  @RequestParam(name = "company", defaultValue = "0") int comId,
                                                  @RequestParam(name = "code",required = false, defaultValue = "") String code,
                                                  @RequestParam(name = "text",required = false, defaultValue = "") String text,
                                                  @RequestParam(name = "dateAsc", defaultValue = "true") boolean dateAsc,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date sDate = formatter.parse(startDate);
            Date eDate = formatter.parse(endDate);
            PageRequest request = PageRequest.of(page - 1, size);
            return JsonResult.success(new PageJson<>(billService.filter2(comId, statusId, code, text, request, dateAsc)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
