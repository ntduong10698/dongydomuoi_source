package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.InvoiceTemplate;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.InvoiceTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/invoice-template")
public class InvoiceTemplatePrivateController {

    @Autowired
    private InvoiceTemplateService invoiceTemplateService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return invoiceTemplateService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<JsonResult> findById(@RequestParam(value = "text", defaultValue = "") String text,
                                               @RequestParam(value = "ascName", defaultValue = "true") boolean ascName,
                                               @RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size",defaultValue = "10") int size){
        try {
            Pageable pageable = PageRequest.of(page-1,size);
            if("".equals(text)){
                return JsonResult.success(new PageJson<InvoiceTemplate>(invoiceTemplateService.findAll(pageable,ascName)));
            }else{
                return JsonResult.success(new PageJson<InvoiceTemplate>(invoiceTemplateService.filter(text,pageable,ascName)));
            }
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody InvoiceTemplate template){
        try{
            return invoiceTemplateService.save(template)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody InvoiceTemplate template){
        try{
            return invoiceTemplateService.save(template)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("id")int id) throws Exception {
        boolean bol=invoiceTemplateService.delete(id);
        try {
            if(bol)
                return JsonResult.deleted();
            return JsonResult.badRequest("Delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
