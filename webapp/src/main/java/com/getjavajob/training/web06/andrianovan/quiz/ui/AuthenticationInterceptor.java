package com.getjavajob.training.web06.andrianovan.quiz.ui;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.*;

/**
 * Created by user on 25.12.2015.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Set<String> excludedURLs = new HashSet<>(Arrays.asList("/", "/login", "/loginCheck", "/search", "/quizzesSearch", "/quizInfo"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (excludedURLs.contains(request.getRequestURI())) {
            return true;
        }
        HttpSession session = request.getSession();
        Object userNameSession = session.getAttribute("userName");
        String userNameCookie;
        String passwordCookie;
        if (userNameSession == null) {
            userNameCookie = getCookieValue(request, COOKIE_USERNAME);
            passwordCookie = getCookieValue(request, COOKIE_PASSWORD);
            if (userNameCookie != null && passwordCookie != null) {
                session.setAttribute("userName", userNameCookie);
                session.setAttribute("password", passwordCookie);
            } else {
                Object requestedURI = request.getRequestURL();
                if (!requestedURI.toString().equalsIgnoreCase("login")) {
                    session.setAttribute("requestedURI", request.getRequestURI());
                }
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
