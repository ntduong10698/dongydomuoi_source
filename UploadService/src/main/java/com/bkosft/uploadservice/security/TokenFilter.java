package com.bkosft.uploadservice.security;

import com.bkosft.uploadservice.model.JsonResult;
import com.bkosft.uploadservice.model.UserJson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@AllArgsConstructor
public class TokenFilter implements Filter {

    private final RestTemplate restTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String token = request.getParameter("token");
//        try {
//            if (token != null) {
//                JsonResult rs = restTemplate.getForObject("https://dev.bksoftwarevn.com/user-service/api/v1/public/check?token=" + token, JsonResult.class);
//                UserJson userJson = new ModelMapper().map(rs.getData(), UserJson.class);
//                if (userJson != null) {
//                    request.setAttribute("user", userJson);
//                    filterChain.doFilter(servletRequest, servletResponse);
//                    return;
//                }
//            }
            filterChain.doFilter(servletRequest, servletResponse);
//        } catch (Exception ex) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }

    }
}
