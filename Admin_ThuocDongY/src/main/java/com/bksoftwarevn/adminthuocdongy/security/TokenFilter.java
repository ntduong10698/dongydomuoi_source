package com.bksoftwarevn.adminthuocdongy.security;


import com.bksoftwarevn.adminthuocdongy.entities.UserJson;
import com.bksoftwarevn.adminthuocdongy.userservice.service.CheckService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class TokenFilter implements Filter {

    private final CheckService checkService;

    public TokenFilter(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
//            if (!request.getRequestURI().contains("/admin-")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            }
            if (request.getRequestURI().contains("/admin-")) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                // response.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=Strict");
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("Authorization")) {
                        UserJson userJson = checkService.check(cookie.getValue());
                        if (userJson != null && userJson.isAdmin()) {
                            request.setAttribute("user", userJson);
                            System.out.println("User: " + userJson.getUsername() + " company: " + userJson.getComId());
                            cookie.setMaxAge(5 * 60 * 60 * 1000);
                            ((HttpServletResponse) servletResponse).addCookie(cookie);
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        }
                    }
                }
                response.sendRedirect("/login");
            } else if (request.getRequestURI().contains("/api/")
                    && !request.getRequestURI().contains("/public/")) {
                handleAuthenApi(servletRequest, servletResponse, filterChain);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        }
    }

    private void handleAuthenApi(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("METHOD: "+request.getMethod());
        String token = request.getParameter("token");
        try {
            if (token != null) {
                System.out.println("Logged");
                UserJson userJson = checkService.check(token, request);
                if (userJson != null) {
                    if (request.getRequestURI().contains("/admin/") && !userJson.isAdmin()) {
                        res.setStatus(401);
                        return;
                    }
                    request.setAttribute("user", userJson);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
            res.setStatus(401);
        } catch (Exception ex) {
            res.setStatus(401);
        }
    }
}
