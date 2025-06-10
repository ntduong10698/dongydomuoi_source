package com.bksoftwarevn.adminthuocdongy.courseservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lecturer;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("api/v1/admin/lecturer")
public class LecturerPrivateController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Lecturer data){
        try {
            data.setDeleted(false);
            return lecturerService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Lecturer data){
        try {
            data.setModify(new Date());
            log.warn("=>>>>  update lecturer id: "+data.getId());
            return lecturerService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id){
        try {
            if (!courseService.findByLecturer(id).isEmpty())
                return JsonResult.badRequest("lecturer still contains course");
            if (lecturerService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
