package com.bksoftwarevn.adminthuocdongy.userservice.controller;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.JsonResult;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.PageJson;
import com.bksoftwarevn.adminthuocdongy.userservice.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/admin/users")
public class AdminUsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<JsonResult> getProfile(HttpServletRequest request,
                                                 @RequestParam(value = "id", defaultValue = "0") int id,
                                                 @RequestParam(value = "text", required = false, defaultValue = "") String text,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
        try {
            AppUser appUser = (AppUser) request.getAttribute("appUser");
            return JsonResult.success(new PageJson<>(userService.findByCompany(appUser.getCompanyId(), id, text, PageRequest.of(page - 1, size))));
        } catch (Exception ex) {
            log.error("Error getProfile: ", ex);
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<JsonResult> getIds(HttpServletRequest request,
                                                 @RequestParam(value = "ids") List<Integer> ids) {
        try {
            AppUser appUser = (AppUser) request.getAttribute("appUser");
            return JsonResult.success(userService.findByIds(appUser.getCompanyId(), ids));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
