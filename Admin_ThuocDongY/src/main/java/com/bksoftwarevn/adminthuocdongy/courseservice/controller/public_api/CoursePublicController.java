package com.bksoftwarevn.adminthuocdongy.courseservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.ActiveJson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import com.bksoftwarevn.adminthuocdongy.entities.UserJson;
import com.bksoftwarevn.adminthuocdongy.userservice.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/courses")
public class CoursePublicController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CheckService checkService;

    @GetMapping("/lecturer/{id}")
    public ResponseEntity<JsonResult> findByLecturer(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(courseService.findByLecturer(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<JsonResult> findByUser(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(courseService.findByUser(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(courseService.findById(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<JsonResult> findByIds(@RequestParam(name = "ids") List<Integer> ids) {
        try {
            return JsonResult.success(courseService.findByIds(ids));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/active")
    public ResponseEntity<JsonResult> activeCourse(@RequestBody ActiveJson json) {
        try {
            UserJson userJson = checkService.check(json.getToken());
            if (userJson != null) {
                courseService.addCourseToUser(json.getCode(), userJson.getId());
                return JsonResult.success("Thêm thành công");
            }
            return JsonResult.badRequest("Thêm thất bại");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
