package com.bksoftwarevn.adminthuocdongy.userservice.controller;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.JsonResult;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.PasswordForm;
import com.bksoftwarevn.adminthuocdongy.userservice.service.UserService;
import com.bksoftwarevn.adminthuocdongy.userservice.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/user")
public class PrivateUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<JsonResult> getProfile(HttpServletRequest request) {
        try {
            AppUser appUser = (AppUser) request.getAttribute("appUser");
            appUser.setPassword(null);
            return JsonResult.success(appUser);
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/profile/password")
    public ResponseEntity<JsonResult> changePassword(@RequestBody PasswordForm form, HttpServletRequest request) {
        try {
            AppUser appUser = (AppUser) request.getAttribute("appUser");
            if (appUser.getPassword().equals(EncodeUtil.getSHA256(form.getOldPass()))){
                appUser.setPassword(EncodeUtil.getSHA256(form.getNewPass()));
                userService.save(appUser);
                appUser.setPassword(null);
                return JsonResult.success(appUser);
            }else return JsonResult.badRequest("wrong pass");

        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/password")
    public ResponseEntity<JsonResult> changePass(HttpServletRequest request, @RequestBody PasswordForm form) {
        try {
            AppUser appUser = (AppUser) request.getAttribute("appUser");
            appUser.setPassword(EncodeUtil.getSHA256(form.getNewPass()));
            userService.save(appUser);
            appUser.setPassword(null);
            return JsonResult.success(appUser);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<JsonResult> putProfile(HttpServletRequest request,
                                                 @RequestBody AppUser appUser) {
        try {
            AppUser user = (AppUser) request.getAttribute("appUser");
            if (user.getUsername().equals(appUser.getUsername()) && user.getCompanyId() == appUser.getCompanyId()) {
                appUser.setPassword(user.getPassword());
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
