package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Contact;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class ContactAdminController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/company/{comId}")
    public ResponseEntity<JsonResult> findByCom(@PathVariable("comId") int comId,
                                                @RequestParam(value = "checked", required = false) Boolean checked,
                                                @RequestParam("page") int page,
                                                @RequestParam("size") int size){
        try {
            PageRequest pageRequest = PageRequest.of(page - 1,size );
            return JsonResult.success(new PageJson<>(contactService.findByCom(comId, checked, pageRequest)));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/contacts/company/{id}/unchecked")
    public ResponseEntity<JsonResult> unchecked(@PathVariable("id") int id){
        try {
            return JsonResult.success(contactService.countUnchecked(id));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/contact")
    public ResponseEntity<JsonResult> update(@RequestBody Contact contact){
        try {
          return JsonResult.updated(contactService.save(contact));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id){
        try {
           if(contactService.delete(id))
            return JsonResult.deleted();
           else return JsonResult.badRequest("fail");
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
