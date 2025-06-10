package com.bksoftwarevn.adminthuocdongy.courseservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.StudentProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/student-products")
public class StudentProductsController {

    @Autowired
    private StudentProductService service;

    @GetMapping
    @ApiOperation(value = "find by course or company", notes = "courseId = 0 => tìm theo company, view= 1-trang chủ, 2-online, 3-offline")
    public ResponseEntity<JsonResult> findByCourse(@RequestParam("course") int courseId,
                                                   @RequestParam("company") int comId,
                                                   @RequestParam(value = "index", required = false) Boolean enableI,
                                                   @RequestParam(value = "online", required = false) Boolean enableOn,
                                                   @RequestParam(value = "offline", required = false) Boolean enableOff){

        try {
           return JsonResult.success(service.findByCourse(courseId, comId, enableI, enableOn, enableOff));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
