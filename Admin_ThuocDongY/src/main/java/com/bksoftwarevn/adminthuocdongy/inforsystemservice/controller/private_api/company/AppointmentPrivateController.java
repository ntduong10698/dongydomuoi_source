package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.private_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Appointment;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
public class AppointmentPrivateController {

    private final AppointmentService appointmentService;


    @GetMapping("/appointments/company/{id}")
    public ResponseEntity<JsonResult> findByCompany(@PathVariable("id") int companyId,
                                                    Pageable pageable) {
        try {
            return JsonResult.success(new PageJson<>(appointmentService.findByCompany(companyId, pageable)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/appointments/new/company/{id}")
    public ResponseEntity<JsonResult> countNewByCompany(@PathVariable("id") int companyId) {
        try {
            return JsonResult.success(appointmentService.countNew(companyId));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable("id") int id) {
        try {
            return appointmentService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("not found"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/appointment")
    public ResponseEntity<JsonResult> update(@RequestBody Appointment appointment) {
        try {
            return appointmentService.save(appointment)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("save fail"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") int id) {
        try {
            if (appointmentService.deleted(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }
}
