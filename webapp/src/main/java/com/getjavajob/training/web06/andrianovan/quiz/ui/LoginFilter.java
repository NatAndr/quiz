package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 07.12.2015.
 */
public class LoginFilter implements Filter {
    private String loginPage;
    private List<String> excludedURLs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.loginPage = filterConfig.getInitParameter("loginPage");
        excludedURLs = new ArrayList<>(Arrays.asList(filterConfig.getInitParameter("excludedURLs").split(";")));
        System.out.println("excludedURLs = " + excludedURLs);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object userNameSession = request.getSession().getAttribute("userName");
        Object passwordSession = request.getSession().getAttribute("password");
        String userNameCookie;
        String passwordCookie;
        String path = String.valueOf(request.getRequestURL());
        System.out.println("path = " + path);
        if (excludedURL(path)) {
            System.out.println("excludedURLs.contains(path)");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (userNameSession == null) {
                userNameCookie = getCookieValue(request, COOKIE_USERNAME);
                passwordCookie = getCookieValue(request, COOKIE_PASSWORD);

                if (userNameCookie != null && passwordCookie != null) {
                    System.out.println("userNameCookie =" + userNameCookie);
                    System.out.println("passwordCookie =" + passwordCookie);
                    request.getSession().setAttribute("userName", userNameCookie);
                    request.getSession().setAttribute("password", passwordCookie);
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    System.out.println("userNameCookie == null or passwordCookie == null");
                    System.out.println("redirect to login");
                    response.sendRedirect("login");
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean excludedURL(String url) {
        for (String excludedURL : excludedURLs) {
            if (url.contains(excludedURL)) {
                return true;
            }
        }
        return false;
    }
}
