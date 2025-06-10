package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.ChapterAdmin;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.ChapterService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/lesson")
public class LessonPrivateController {

    @Autowired
    private LessonService lessonService;
    
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(lessonService.findById(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<JsonResult> findByCourse(@PathVariable(name = "id") int id) {
        try {
            List<Chapter> chapters = chapterService.findByCourse(id);
            List<ChapterAdmin> chapterViews = new LinkedList<>();
            chapters.forEach(chapter -> chapterViews.add(ChapterAdmin.builder()
                    .chapter(chapter)
                    .lessons(lessonService.findByChapter(chapter.getId()))
                    .build()
            ));
            return JsonResult.success(chapterViews);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Lesson data,
                                             @RequestParam(name = "chapter") int id) {
        try {
            data.setDeleted(false);
            data.setChapter(chapterService.findById(id).orElse(null));
            if (data.getChapter() == null) return JsonResult.badRequest("Chapter is not exist");
            return lessonService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Lesson data) {
        try {
            return lessonService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (lessonService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
