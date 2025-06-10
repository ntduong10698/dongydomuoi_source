package com.bksoftwarevn.adminthuocdongy.marketing.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Popup;
import com.bksoftwarevn.adminthuocdongy.marketing.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.marketing.service.PopupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class PopupAdmin {

    @Autowired
    private PopupService popupService;

    @GetMapping("/popups/company/{id}")
    public ResponseEntity<JsonResult> findByComId(@PathVariable("id") int id) {
        try {
            return JsonResult.success(popupService.findByCom(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/popup")
    public ResponseEntity<JsonResult> post(@RequestBody Popup popup){
        try {
            popup.setDeleted(false);
            return JsonResult.uploaded(popupService.save(popup));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/popup")
    public ResponseEntity<JsonResult> put(@RequestBody Popup popup){
        try {
            return JsonResult.updated(popupService.save(popup));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/popup/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id){
        try {
            popupService.delete(id);
            return JsonResult.deleted();
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/popup/{id}/show")
    public ResponseEntity<JsonResult> put(@PathVariable("id") int id){
        try {
            Popup popup = popupService.findById(id).get();
            popupService.setAllHide(popup.getCompanyId());
            popupService.setShow(id);
            return JsonResult.success("showed");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
