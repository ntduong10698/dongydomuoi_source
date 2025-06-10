package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Status;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/status")
public class StatusPrivateController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return statusService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Status status){
        try{
            return statusService.save(status)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Status status){
        try{
            return statusService.save(status)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("id")int id) throws Exception {
        boolean bol=statusService.delete(id);
        try {
            if(bol)
                return JsonResult.deleted();
            return JsonResult.badRequest("Delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
