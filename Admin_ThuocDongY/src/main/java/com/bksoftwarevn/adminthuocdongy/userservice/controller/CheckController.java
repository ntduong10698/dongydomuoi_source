package com.bksoftwarevn.adminthuocdongy.userservice.controller;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.JsonResult;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.UserJson;
import com.bksoftwarevn.adminthuocdongy.userservice.security.JWTService;
import com.bksoftwarevn.adminthuocdongy.userservice.security.SecurityConstants;
import com.bksoftwarevn.adminthuocdongy.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/public/check")
public class CheckController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<JsonResult> check(HttpServletRequest req,
                                            @RequestParam("token") String token){

        if (token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            UserJson userJson = getAuthentication(token);
            if (userJson != null)
                return JsonResult.success(userJson);
        }
        return ResponseEntity.badRequest().body(null);
    }

    //  read token và cấp quyền
    private UserJson getAuthentication(String token) {
        if (token != null) {
            String usernameAndComId = jwtService.decode(token);
            if (usernameAndComId != null) {
                AppUser appUser = userService.findByUsernameAndComId(usernameAndComId).orElse(null);
                if (appUser != null) {
                    System.out.println("User Principal: " + usernameAndComId);
                    return UserJson.builder()
                            .username(appUser.getUsername())
                            .comId(appUser.getCompanyId())
                            .id(appUser.getId())
                            .admin(appUser.isAdmin())
                            .build();
                }
            }
        }
        return null;
    }
}
