package com.bkosft.uploadservice.controller;

import com.bkosft.uploadservice.model.JsonResult;
import com.bkosft.uploadservice.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;

import static com.bkosft.uploadservice.util.Company.*;

@RestController
@RequestMapping("api/v1/public")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Value(value = "${spring.download.link-prefix}")
    private String downloadPrefix;

    @PostMapping("/upload/company/{id}")
    public ResponseEntity<JsonResult> uploadFiles(@RequestParam("files") MultipartFile[] multipartFiles, @PathVariable("id") int comId) {
        try {
            return JsonResult.uploaded(uploadService.upload(getCompanyName(comId), multipartFiles));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/log")
    public ResponseEntity<JsonResult> getLog() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("log/spring.log"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            String content = stringBuilder.toString();
            return JsonResult.success(content);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/folder/company/{id}")
    public ResponseEntity<JsonResult> createFolder(@RequestParam("name") String name, @PathVariable("id") int comId) {
        try {
            return JsonResult.uploaded(uploadService.createFolder(getCompanyName(comId), name));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/link/company/{id}")
    public ResponseEntity<JsonResult> getLink(@PathVariable("id") int comId) {
        return JsonResult.success(downloadPrefix + getCompanyName(comId));
    }

    @GetMapping("/logo/company/{id}")
    public ResponseEntity<JsonResult> getLogo(@PathVariable("id") int comId) {
        return JsonResult.success(String.join("/", downloadPrefix, getCompanyName(comId), "logo", "logo-" + getCompanyName(comId) + ".png"));
    }
}
