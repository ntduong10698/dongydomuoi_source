package com.bksoftwarevn.adminthuocdongy.courseservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.ChapterView;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.LessonView;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.ChapterService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public/lessons")
public class LessonPublicController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/course/{id}")
    public ResponseEntity<JsonResult> findByCourse(@PathVariable(name = "id") int id) {
        try {
            List<Chapter> chapters = chapterService.findByCourse(id);
            List<ChapterView> chapterViews = new LinkedList<>();
            chapters.forEach(chapter -> chapterViews.add(ChapterView.builder()
                    .chapter(chapter.getName())
                    .lessons(lessonService.findByChapter(chapter.getId()).stream().map(LessonView::new).collect(Collectors.toList()))
                    .build()
            ));
            return JsonResult.success(chapterViews);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
