package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Customer;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.UserJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CompanyService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/admin/customer")
@AllArgsConstructor
public class CustomerAdminController {

    private final CustomerService customerService;

    private final CompanyService companyService;


    @PostMapping
    public ResponseEntity<JsonResult> post(@RequestBody Customer customer, HttpServletRequest request) {
        try {
            UserJson userJson = (UserJson) request.getAttribute("user");
            customer.setCompany(companyService.findById( userJson.getComId()).get());
          return JsonResult.uploaded(customerService.save(customer));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> put(@RequestBody Customer customer) {
        try {
            return JsonResult.updated(customerService.save(customer));
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id){
        try {
            customerService.deleted(id);
            return JsonResult.deleted();
        }catch (Exception ex){
            return JsonResult.error(ex);
        }
    }
}
