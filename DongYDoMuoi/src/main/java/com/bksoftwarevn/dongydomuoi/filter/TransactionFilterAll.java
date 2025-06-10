package com.bksoftwarevn.dongydomuoi.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class TransactionFilterAll implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        StringBuffer urlString = req.getRequestURL();
        String queryString = req.getQueryString();
        if(queryString != null) {
            urlString.append("?" + queryString);
        }
        req.setAttribute("image", "https://cdn.bksoftwarevn.com/resources/micro-upload//dong-y/logo/logo-dong-y.png");
        req.setAttribute("title", "Đông Y Đỗ Mười");
        req.setAttribute("description", "Đông Y Đỗ Mười - Bài Thuốc Dân Tộc");
        req.setAttribute("url", urlString);
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
