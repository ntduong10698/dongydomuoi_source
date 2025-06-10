package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Course;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LecturerService;
import com.bksoftwarevn.adminthuocdongy.entities.RestBuilder;
import com.bksoftwarevn.adminthuocdongy.service.impl.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/course")
public class CoursePrivateController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private RestService restService;

    @Autowired
    private RestTemplate template;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Course data,
                                             @RequestParam(name = "lecture") int id) {
        try {
            data.setDeleted(false);
            data.setLecturer(lecturerService.findById(id).orElse(null));
            if (data.getLecturer() == null) return JsonResult.badRequest("lecturer is not exist");
            return courseService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Course data) {
        try {
            return courseService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (courseService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/{id}/code")
    public ResponseEntity<JsonResult> createCode(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(courseService.generateCode(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<JsonResult> getUsers(HttpServletRequest request,
                                               @PathVariable(name = "id") int id,
                                               @RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            String token = request.getParameter("token");
            Page<Integer> userIds = courseService.findUsersByCourse(id, PageRequest.of(page - 1, size));
            Object data = restService.callGet(RestBuilder
                    .build()
                    .service("")
                    .uri("api/v1/admin/users/ids")
                    .param("ids", userIds.stream().map(userId -> userId + "").collect(Collectors.joining(",")))
                    .param("token", token));
//            JsonResult data = template.getForObject("http://localhost:9500/api/v1/admin/users/ids?ids=40&token=BKSofteyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYXRhbGllcG11LTEiLCJleHAiOjE2Mjc0MzcyMTF9.VoIzTKonGKK1az8C6jvLrXRzCUiaYTgADpyM9STfnSop442cmFtY3Xskt8w_0BotmXGxKD9LytCsWUtsjC7Rxw&", JsonResult.class);
            PageJson<Object> pageJson = new PageJson<>();
            pageJson.setTotalPages(userIds.getTotalPages());
            pageJson.setTotalElements(userIds.getTotalElements());
            pageJson.setContent((List<Object>) data);
            return JsonResult.success(pageJson);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/{id}/user/{userId}")
    public ResponseEntity<JsonResult> addCourseToUser(@PathVariable("id") int courseId,
                                                      @PathVariable("userId") int userId) {
        try {
            if (courseService.addCourseToUser(courseId, userId))
                return JsonResult.success("Thêm thành công");
            return JsonResult.badRequest("Tài khoản đã sở hữu khóa học");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<JsonResult> removeCourseFromUser(@PathVariable("id") int courseId,
                                                      @PathVariable("userId") int userId) {
        try {
            if (courseService.removeCoursefromUser(courseId, userId))
                return JsonResult.success("Xoá thành công");
            return JsonResult.badRequest("Tài khoản không sở hữu khóa học");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
