package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 07.12.2015.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object userNameSession = request.getSession().getAttribute("userName");
//        String passwordSession = request.getSession().getAttribute("password").toString();
        String userNameCookie;
        String passwordCookie;

        String path = request.getRequestURI();

        if (userNameSession == null) {
            userNameCookie = getCookieValue(request, COOKIE_USERNAME);
            passwordCookie = getCookieValue(request, COOKIE_PASSWORD);

            if (userNameCookie != null && passwordCookie != null) {
                request.login(userNameCookie, passwordCookie);
                request.getSession().setAttribute("userName", userNameCookie);
                request.getSession().setAttribute("password", passwordCookie);
                addCookie(response, COOKIE_USERNAME, userNameCookie, COOKIE_AGE);
                addCookie(response, COOKIE_PASSWORD, passwordCookie, COOKIE_AGE);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("login");
//                removeCookie(response, COOKIE_USERNAME);
//                removeCookie(response, COOKIE_PASSWORD);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
