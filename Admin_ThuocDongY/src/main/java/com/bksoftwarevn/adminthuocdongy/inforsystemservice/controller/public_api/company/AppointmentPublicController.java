package com.bksoftwarevn.adminthuocdongy.inforsystemservice.controller.public_api.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Appointment;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.AppointmentService;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/public/appointment")
@AllArgsConstructor
public class AppointmentPublicController {

    private final AppointmentService appointmentService;

    private final BranchService branchService;

    @PostMapping("/branch/{branch_id}")
    public ResponseEntity<JsonResult> upload(@RequestBody Appointment appointment,
                                             @PathVariable("branch_id") int branch_id) {
        try {
            return branchService.findById(branch_id)
                    .map(branch -> {
                        appointment.setDeleted(false);
                        appointment.setBranch(branch);
                        try {
                            return JsonResult.uploaded(appointmentService.save(appointment));
                        } catch (Exception e) {
                            return JsonResult.error(e);
                        }
                    }).orElseThrow(() -> new Exception("branch not found"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
