package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.StudentProduct;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.StudentProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/student-product")
public class StudentProductPrivateController {

    @Autowired
    private StudentProductService service;

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody StudentProduct data,
                                             @RequestParam(name = "course") int id) {
        try {
            data.setDeleted(false);
            data.setCourse(courseService.findById(id).orElse(null));
            if (data.getCourse() == null) return JsonResult.badRequest("Course is not exist");
            return JsonResult.uploaded(service.save(data));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody StudentProduct data) {
        try {
            return service.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (service.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
