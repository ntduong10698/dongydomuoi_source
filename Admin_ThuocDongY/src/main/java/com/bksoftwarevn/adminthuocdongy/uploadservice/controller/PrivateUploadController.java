package com.bksoftwarevn.adminthuocdongy.uploadservice.controller;

import com.bksoftwarevn.adminthuocdongy.uploadservice.model.JsonResult;
import com.bksoftwarevn.adminthuocdongy.uploadservice.model.RobotForm;
import com.bksoftwarevn.adminthuocdongy.uploadservice.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bksoftwarevn.adminthuocdongy.uploadservice.util.Company.*;


@RestController
@RequestMapping("api/v1/admin")
public class PrivateUploadController {

    @Autowired
    private UploadService uploadService;

    @Value(value = "${spring.download.link-prefix}")
    private String downloadPrefix;

    @PostMapping("/upload/logo/company/{id}")
    public ResponseEntity<JsonResult> uploadLogo(@RequestParam("files") MultipartFile file, @PathVariable("id") int comId) {
        try {
            String company = getCompanyName(comId);
            uploadService.newLogo(company, file);
            return JsonResult.updated("Logo");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/upload/robot/company/{id}")
    public ResponseEntity<JsonResult> uploadRobot(@RequestParam("files") MultipartFile file, @PathVariable("id") int comId) {
        try {
            String company = getCompanyConfig(comId);
            uploadService.newRobot(company, file);
            return JsonResult.updated("Robot");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/upload/robot/company/{id}")
    public ResponseEntity<JsonResult> updateRobot(@RequestBody RobotForm form, @PathVariable("id") int comId) {
        try {
            String company = getCompanyConfig(comId);
            uploadService.newRobot(company, form.getContent());
            return JsonResult.updated("Robot");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/robot/company/{id}")
    public ResponseEntity<JsonResult> getRobot(@PathVariable("id") int comId) {
        try {
            String company = getCompanyConfig(comId);
            return JsonResult.success(uploadService.getRobot(company));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
