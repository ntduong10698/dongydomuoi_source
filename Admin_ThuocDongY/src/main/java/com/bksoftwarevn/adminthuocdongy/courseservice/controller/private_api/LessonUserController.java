package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasLesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.ChapterUser;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.UserJson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.UserLesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.ChapterService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user/lessons")
public class LessonUserController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(HttpServletRequest request,
                                               @PathVariable(name = "id") int id) {
        try {
            UserJson user = (UserJson) request.getAttribute("user");
            Lesson lesson = lessonService.findById(id).get();
            if (courseService.checkUser(user.getId(), lesson.getChapter().getCourse().getId()))
//                if (lessonService.checkViewed(user.getId(), id))
                    return JsonResult.success(lesson);
            return JsonResult.badRequest("unauthen");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<JsonResult> learn(@PathVariable("lessonId") int lessonId, HttpServletRequest request) {
        try {
            UserJson user = (UserJson) request.getAttribute("user");
            Lesson lesson = lessonService.findById(lessonId).get();
            if (courseService.checkUser(user.getId(), lesson.getChapter().getCourse().getId())) {
                lessonService.userLearn(user.getId(), lessonId);
                return JsonResult.success("learned");
            }
            return JsonResult.badRequest("unauthen");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/course/{course-id}")
    public ResponseEntity<JsonResult> findByCourse(HttpServletRequest request,
                                                   @PathVariable(name = "course-id") int id) {
        try {
            UserJson user = (UserJson) request.getAttribute("user");
            if (courseService.checkUser(user.getId(), id)) {
                //prepare data
                List<Chapter> chapters = chapterService.findByCourse(id);
                List<Lesson> lessons = lessonService.findByCourse(id);
                List<ChapterUser> chapterUsers = new LinkedList<>();
                List<UserHasLesson> userHasLessons = lessonService.userLearned(user.getId(), id);

                chapters.forEach(chapter -> chapterUsers.add(ChapterUser.builder()
                        .chapter(chapter.getName())
                        .lessons(lessons.stream()
                                .filter(l -> l.getChapter().getId() == chapter.getId())
                                .map(lesson -> UserLesson.builder()
                                        .lesson(lesson)
                                        .view(userHasLessons.stream()
                                                .filter(uhl -> uhl.getId().getLessonId() == lesson.getId())
                                                .findFirst()
                                                .map(UserHasLesson::getView)
                                                .orElse(0))
                                        .build())
                                .collect(Collectors.toList()))
                        .build()
                ));

                return JsonResult.success(chapterUsers);
            }
            return JsonResult.badRequest("unauthen");
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{id}/next")
    public ResponseEntity<JsonResult> nextLesson(HttpServletRequest request, @PathVariable(name = "id") int id) {
        try {
            UserJson user = (UserJson) request.getAttribute("user");
            Lesson lesson = lessonService.findById(id).get();
            if (courseService.checkUser(user.getId(), lesson.getChapter().getCourse().getId())) {
                List<Lesson> lessons = lessonService.findByCourse(lesson.getChapter().getCourse().getId());
                int index = 0;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getId() == lesson.getId()) index = i;
                }
                if (index < lessons.size())
                    return JsonResult.success(lessons.get(index + 1));
                return JsonResult.badRequest("no more");
            }
            return JsonResult.badRequest("unauthen");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }

    }

    @GetMapping("/{id}/previous")
    public ResponseEntity<JsonResult> previousLesson(HttpServletRequest request, @PathVariable(name = "id") int id) {
        try {
            UserJson user = (UserJson) request.getAttribute("user");
            Lesson lesson = lessonService.findById(id).get();
            if (courseService.checkUser(user.getId(), lesson.getChapter().getCourse().getId())) {
                List<Lesson> lessons = lessonService.findByCourse(lesson.getChapter().getCourse().getId());
                int index = 0;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getId() == lesson.getId()) index = i;
                }
                if (index > 0)
                    return JsonResult.success(lessons.get(index - 1));
                return JsonResult.badRequest("no more");
            }
            return JsonResult.badRequest("unauthen");

        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
