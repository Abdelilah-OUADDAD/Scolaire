package com.System.Scolaire.Mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();

        // 🟢 Public URLs (without Login)
        if (uri.equals("/") ||
                uri.equals("/Login") ||
                uri.equals("/LoginP") ||
                uri.startsWith("/css/") ||
                uri.startsWith("/js/") ||
                uri.startsWith("/images/") ||
                uri.startsWith("/webjars/") ||
                uri.equals("/error")) {
            return true;
        }

        // 🔴 Check Session
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("isLoggedIn") == null) {
            response.sendRedirect("/Login");
            return false;
        }

        return true;
    }
}
