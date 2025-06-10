package com.bksoftwarevn.adminthuocdongy.userservice.controller;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.JsonResult;
import com.bksoftwarevn.adminthuocdongy.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @PutMapping("/profile")
    public ResponseEntity<JsonResult> putProfile(HttpServletRequest request,
                                                 @RequestBody AppUser appUser) {
        try {
            AppUser user = (AppUser) request.getAttribute("appUser");
            AppUser modifier = userService.findById(appUser.getId()).get();
            if (user.getCompanyId() == appUser.getCompanyId()) {
                appUser.setPassword(modifier.getPassword());
                return userService.save(appUser)
                        .map(saved -> {
                            appUser.setPassword(null);
                            return JsonResult.success(appUser);
                        })
                        .orElse(JsonResult.badRequest("data is invalid"));
            }
            return JsonResult.badRequest("no permission");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
