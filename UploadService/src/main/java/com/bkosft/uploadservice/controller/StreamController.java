package com.bkosft.uploadservice.controller;

import com.bkosft.uploadservice.service.VideoStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.bkosft.uploadservice.util.Company.*;


@RestController
@RequestMapping("api/v1/public/stream")
public class StreamController {

    @Autowired
    private VideoStreamService videoStreamService;

    @GetMapping("/company/{id}/{fileType}")
    public Mono<ResponseEntity<byte[]>> getStream(@RequestHeader(value = "Range", required = false) String httpRangeList,
                                                  @PathVariable("fileType") String type,
                                                  @RequestParam("fileName") String name,
                                                  @PathVariable("id") int comId){
        return Mono.just(videoStreamService.prepareContent(getCompanyName(comId),name, type, httpRangeList));
    }
}
