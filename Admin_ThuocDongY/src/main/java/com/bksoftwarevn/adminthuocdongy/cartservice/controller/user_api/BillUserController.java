package com.bksoftwarevn.adminthuocdongy.cartservice.controller.user_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user/bills")
public class BillUserController {

    @Autowired
    private BillService billService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<JsonResult> findByCusId(@PathVariable(name = "id") int id,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            return JsonResult.success(new PageJson<Bill>(billService.findByCustomerId(id, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
