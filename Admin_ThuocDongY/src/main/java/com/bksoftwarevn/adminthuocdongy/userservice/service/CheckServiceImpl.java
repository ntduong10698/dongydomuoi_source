package com.bksoftwarevn.adminthuocdongy.userservice.service;

import com.bksoftwarevn.adminthuocdongy.entities.UserJson;
import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.security.JWTService;
import com.bksoftwarevn.adminthuocdongy.userservice.security.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j2
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService{
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    public UserJson check(String token) {
        if (token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return getAuthentication(token);
        }
        return null;
    }

    @Override
    public UserJson check(String token, HttpServletRequest request) {
        if (token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return getAuthentication(token, request);
        }
        return null;
    }

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

    private UserJson getAuthentication(String token, HttpServletRequest request) {
        log.info("getAuthentication");
        if (token != null) {
            String usernameAndComId = jwtService.decode(token);
            if (usernameAndComId != null) {
                AppUser appUser = userService.findByUsernameAndComId(usernameAndComId).orElse(null);
                if (appUser != null) {
                    request.setAttribute("appUser", appUser);
                    log.info("User Principal: {}", usernameAndComId);
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
