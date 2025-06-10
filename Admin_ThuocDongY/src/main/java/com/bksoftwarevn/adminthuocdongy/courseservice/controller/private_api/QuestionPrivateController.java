package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Question;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.QuestionOption;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LessonService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/question")
public class QuestionPrivateController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LessonService lessonService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Question data,
                                             @RequestParam(name = "lesson") int id) {
        try {
            data.setDeleted(false);
            data.setLesson(lessonService.findById(id).orElse(null));
            if (data.getLesson() == null) return JsonResult.badRequest("Lesson is not exist");
            List<QuestionOption> questionOptions = data.getOptions();
            data.setOptions(null);
            return questionService.save(data)
                    .map(saved -> {
                        questionOptions.forEach(option -> option.setQuestionId(saved.getId()));
                        saved.setOptions(questionService.saveAll(questionOptions));
                        return JsonResult.success(saved);
                    })
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Question data) {
        try {
            return questionService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (questionService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
