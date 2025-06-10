package com.bksoftwarevn.adminthuocdongy.userservice.controller;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.*;
import com.bksoftwarevn.adminthuocdongy.userservice.security.JWTService;
import com.bksoftwarevn.adminthuocdongy.userservice.security.SecurityConstants;
import com.bksoftwarevn.adminthuocdongy.userservice.service.UserService;
import com.bksoftwarevn.adminthuocdongy.userservice.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/public")
public class PublicUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server-url}")
    private String SERVER_URL;

    @GetMapping("/feedbacks/company/{comId}")
    public ResponseEntity<JsonResult> getProfile(@PathVariable("comId") int comId) {
        try {
            return JsonResult.success(userService.getFeedBack(comId));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResult> login(@RequestBody LoginForm form) {
        try {
            Optional<AppUser> userOptional = userService.checkLogin(form);
            if (userOptional.isPresent()) {
                AppUser user = userOptional.get();
                JWTEntity jwtEntity = new JWTEntity();
                jwtEntity.setCode(jwtService.generateToken(user.getUsername() + "-" + user.getCompanyId()));
                jwtEntity.setDuration(SecurityConstants.EXPIRATION_TIME);
                return JsonResult.success(jwtEntity);
            }
            return JsonResult.badRequest("username or password is incorrect");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/login-admin")
    public ResponseEntity<JsonResult> loginAdmin(@RequestBody LoginForm form) {
        try {
            Optional<AppUser> userOptional = userService.checkLogin(form);
            if (userOptional.isPresent()) {
                AppUser user = userOptional.get();
                if (!user.isAdmin()) return JsonResult.badRequest("not admin");
                JWTEntity jwtEntity = new JWTEntity();
                jwtEntity.setCode(jwtService.generateToken(user.getUsername() + "-" + user.getCompanyId()));
                jwtEntity.setDuration(SecurityConstants.EXPIRATION_TIME);
                return JsonResult.success(jwtEntity);
            }
            return JsonResult.badRequest("username or password is incorrect");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<JsonResult> register(@RequestBody RegisterForm form) {
        try {
            if (userService.checkRegister(form.getUsername(), form.getEmail(), form.getCompanyId())) {
                return JsonResult.badRequest("username or email existed");
            } else {
                AppUser user = new AppUser();
                user.setUsername(form.getUsername());
                user.setPassword(EncodeUtil.getSHA256(form.getPassword()));
                user.setEmail(form.getEmail());
                user.setName(form.getName());
                user.setCompanyId(form.getCompanyId());
                user.setDeleted(false);
                return userService.save(user)
                        .map(JsonResult::uploaded)
                        .orElse(JsonResult.badRequest("data is invalid"));
            }
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/forget-password/{email}/company/{company}")
    public ResponseEntity<JsonResult> forget(@PathVariable("email") String email, @PathVariable("company") int comId) {
        try {
            Optional<AppUser> user = userService.findUserByEmailAndCom(email, comId);
            if (user.isPresent()) {
                JWTEntity jwtEntity = new JWTEntity();
                jwtEntity.setCode(jwtService.generateToken(user.get().getUsername() + "-" + user.get().getCompanyId()));
                jwtEntity.setDuration(SecurityConstants.EXPIRATION_TIME);
                String link = "https://admin.nataliepmu.com/forget?token=" + jwtEntity.getCode();
                if (user.get().isAdmin())
                    link = "https://admin.nataliepmu.com/forget?role=admin&token=" + jwtEntity.getCode();
                MailJson mail = new MailJson("Quên mật khẩu", "Truy cập đường dẫn sau để thay đổi mật khẩu " + link);
                restTemplate.postForObject(SERVER_URL + "api/v1/public/email/company/" + comId + "/email/" + email + "?password=Bksoftwarevn", mail, Object.class);
            }
            return JsonResult.success("sent");
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }
}
