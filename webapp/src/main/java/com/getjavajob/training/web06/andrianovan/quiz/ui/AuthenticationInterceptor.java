package com.getjavajob.training.web06.andrianovan.quiz.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.COOKIE_USERNAME;
import static com.getjavajob.training.web06.andrianovan.quiz.ui.CookieHelper.getCookieValue;

/**
 * Created by user on 25.12.2015.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final Logger debugLogger = LoggerFactory.getLogger("DebugLogger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object userNameSession = session.getAttribute("userName");
        String userNameCookie;
        if (userNameSession == null) {
            debugLogger.debug("userName Session == null");
            userNameCookie = getCookieValue(request, COOKIE_USERNAME);
            if (userNameCookie != null) {
                debugLogger.debug("Take userName Session from cookie");
                session.setAttribute("userName", userNameCookie);
            } else {
                debugLogger.debug("userName Cookie == null");
                String requestURI = request.getRequestURI();
                debugLogger.debug("requested URI = " + requestURI);
                if (!requestURI.equals("/login")) {
                    session.setAttribute("requestedURI", requestURI);
                }
                response.sendRedirect("/login");
                debugLogger.debug("redirect to login page");
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
