package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.ChapterService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/chapter")
public class ChapterPrivateController {
    
    @Autowired
    private ChapterService chapterService;
    
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Chapter data,
                                             @RequestParam(name = "course") int id) {
        try {
            data.setDeleted(false);
            data.setCourse(courseService.findById(id).orElse(null));
            if (data.getCourse() == null) return JsonResult.badRequest("Course is not exist");
            return chapterService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Chapter data) {
        try {
            return chapterService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (chapterService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
