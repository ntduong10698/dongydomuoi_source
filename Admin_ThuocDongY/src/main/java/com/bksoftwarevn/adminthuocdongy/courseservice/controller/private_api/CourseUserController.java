package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Course;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.UserJson;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("api/v1/user/courses")
public class CourseUserController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseEntity<JsonResult> findByUser(HttpServletRequest request) {
        try {
            System.out.println(request.getAttribute("user"));
            UserJson user = (UserJson) request.getAttribute("user");
            Set<Course> courseSet = courseService.findByUser(user.getId());
            System.out.println(courseSet);
            return JsonResult.success(courseSet);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<JsonResult> findIdsByUser(HttpServletRequest request) {
        try {
            UserJson user = (UserJson) request.getAttribute("user");
            return JsonResult.success(courseService.findIdsByUser(user.getId()));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
