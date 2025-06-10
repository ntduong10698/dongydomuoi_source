package com.bksoftwarevn.adminthuocdongy.userservice.security;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import com.bksoftwarevn.adminthuocdongy.userservice.repo.UserRepo;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class JWTAuthorizationFilter implements Filter {

    private UserRepo userRepo;

    private JWTService jwtService;

    public JWTAuthorizationFilter(UserRepo userRepo, JWTService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    //  read token và cấp quyền
    private AppUser getAuthentication(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (token != null) {
            String usernameAndComId = jwtService.decode(token);
            if (usernameAndComId != null) {
                String username = usernameAndComId.split("-")[0];
                int comId = Integer.parseInt(usernameAndComId.split("-")[1]);
                AppUser appUser = userRepo.findByUsername(username, comId).orElse(null);
                if (appUser != null) {
                    System.out.println("User Principal: " + appUser.getUsername() + " comId: " + comId);
                    return appUser;
                }
            }
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            AppUser appUser = getAuthentication(request);
            if (appUser != null) {
                if (request.getRequestURI().contains("/admin/") && !appUser.isAdmin()){
                    response.setStatus(401);
                    return;
                }
                request.setAttribute("user", appUser);
                filterChain.doFilter(request, response);
            } else
                response.setStatus(401);
        } catch (Exception e) {
            response.setStatus(401);
        }
    }
}
