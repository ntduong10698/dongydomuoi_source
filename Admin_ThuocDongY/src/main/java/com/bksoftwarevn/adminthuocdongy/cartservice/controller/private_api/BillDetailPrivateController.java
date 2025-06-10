package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.*;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.BillDetail;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.BillDetailService;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/bill-detail")
public class BillDetailPrivateController {

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private BillService billService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return billDetailService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/bill/{id}")
    public ResponseEntity<JsonResult> findByBillId(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(billDetailService.findByBillId(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }


    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody BillDetail billDetail) {
        try {
            return billDetailService.save(billDetail)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
