package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Part;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.ImageJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartDetailService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public/contents")
public class ContentPublicController {

    @Autowired
    private PartService partService;

    @Autowired
    private PartDetailService partDetailService;

    @GetMapping("/company/{comId}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable("comId") int comId,
                                                @RequestParam(value = "code", required = false, defaultValue = "") String code){
        try {
            if (code == null) code = "";
            List<Part> parts = partService.findByCompany(comId, code);
            return JsonResult.success(parts.stream().map(part -> {
                part.setCompany(null);
                ImageJson imageJson = new ImageJson();
                imageJson.setPart(part);
                imageJson.setPartDetails(partDetailService.findByPartId(part.getId()).stream().filter(detail -> detail.isEnable()).collect(Collectors.toList()));
                return imageJson;
            }));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
