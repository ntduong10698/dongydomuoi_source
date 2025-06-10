package com.bksoftwarevn.adminthuocdongy.courseservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/chapters")
public class ChapterPublicController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/course/{id}")
    public ResponseEntity<JsonResult> findByLecturer(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(chapterService.findByCourse(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(chapterService.findById(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
