package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Contact;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.ContactService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.SendMailIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/contact")
public class ContactPublicController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SendMailIService sendMailService;

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Contact contact) {
        try {
            Contact saved = contactService.save(contact);
            Company company = companyService.findById(contact.getCompanyId()).orElse(null);
            sendMailService.sendHtmlMail(company.getEmail(),
                    company.getNameCompany() + " có khách hàng liên hệ mới.",
                    contact.getName() + " vừa gửi yêu cầu liên hệ.");
            return JsonResult.uploaded(saved);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonResult.error(ex);
        }
    }
}
