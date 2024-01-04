package com.revature.revpay.configuration;

import com.revature.revpay.entities.Admin;
import com.revature.revpay.entities.User;
import com.revature.revpay.services.AdminService;
import com.revature.revpay.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomFilterBean extends GenericFilterBean {
    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public CustomFilterBean(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = String.valueOf(request.getRequestURL());
        if (url.contains("/sign-in") || url.contains("/sign-up") || url.contains("/admin-sign-in")) {
            filterChain.doFilter(request,response);
            return;
        }
        if (request.getMethod().equals("OPTIONS")){
            filterChain.doFilter(request,response);
            return;
        }
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("username")) {
                    Optional<User> found = userService.findByUsername(c.getValue());
                    if (found.isPresent()) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    return;
                }
                if (c.getName().equals("admin")) {
                    Optional<Admin> found = adminService.findByUsername(c.getValue());
                    if (found.isPresent()) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    return;
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
