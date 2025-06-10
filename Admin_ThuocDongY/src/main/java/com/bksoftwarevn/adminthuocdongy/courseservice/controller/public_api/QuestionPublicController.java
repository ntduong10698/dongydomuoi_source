package com.bksoftwarevn.adminthuocdongy.courseservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.QuestionOption;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public/questions")
public class QuestionPublicController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/lesson/{id}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(questionService.findByLesson(id)
                    .stream()
                    .peek(q -> q.setLesson(null)) // ẩn lesson
                    .peek(q -> q.getOptions().sort(Comparator.comparingInt(QuestionOption::getId))) // sắp xếp lựa chọn
                    .collect(Collectors.toList()));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(questionService.findById(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
