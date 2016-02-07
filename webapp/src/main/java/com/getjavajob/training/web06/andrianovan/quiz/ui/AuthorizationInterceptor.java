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
 * Created by user on 07.02.2016.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object userNameSession = session.getAttribute("userName");
        logger.debug("userName Session: {}", userNameSession);
        if (userNameSession == null) {
            String userNameCookie = getCookieValue(request, COOKIE_USERNAME);
            if (userNameCookie != null) {
                logger.debug("Take userName Session from cookie");
                session.setAttribute("userName", userNameCookie);
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
