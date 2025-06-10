package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.MailJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.SendMailIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/email")
public class SendMailIController {

    @Autowired
    private SendMailIService sendMailService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/company/{id}")
    public ResponseEntity<JsonResult> sendMail(@PathVariable("id") int id,
                                               @RequestBody MailJson mailJson,
                                               @RequestParam("password") String password) {
        try {
            if (!password.equals("Bksoftwarevn"))
                return JsonResult.badRequest("wrong pass");
            Company company = companyService.findById(id).get();
            sendMailService.sendHtmlMail(company.getEmail(), mailJson.getHeader(), mailJson.getContent());
            return JsonResult.success("sent");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/company/{id}/email/{email}")
    public ResponseEntity<JsonResult> sendMail(@PathVariable("id") int id,
                                               @PathVariable("email") String email,
                                               @RequestBody MailJson mailJson,
                                               @RequestParam("password") String password) {
        try {
            if (!password.equals("Bksoftwarevn"))
                return JsonResult.badRequest("wrong pass");
            Company company = companyService.findById(id).get();
//            if (company.getId() == 1)
//                sendMailService.natalieMail(email,company.getNameCompany() + " - " +  mailJson.getHeader(), mailJson.getContent());
//            else
                sendMailService.sendHtmlMail(email,company.getNameCompany() + " - " +  mailJson.getHeader(), mailJson.getContent());
            return JsonResult.success("sent");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }
}
