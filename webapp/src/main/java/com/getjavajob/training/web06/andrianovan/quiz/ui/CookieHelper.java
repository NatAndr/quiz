package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 07.12.2015.
 */
public class CookieHelper {

    public static final String COOKIE_USERNAME = "userName";
    public static final String COOKIE_PASSWORD = "password";
    public static final int COOKIE_AGE = 9999999;//30 * 60;

    public static String getCookieValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse resp, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        resp.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse resp, String name) {
        addCookie(resp, name, null, 0);
    }
}
