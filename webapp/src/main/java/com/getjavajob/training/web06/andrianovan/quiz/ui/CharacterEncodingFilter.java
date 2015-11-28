package com.getjavajob.training.web06.andrianovan.quiz.ui;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Nat on 23.11.2015.
 */
public class CharacterEncodingFilter implements javax.servlet.Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (this.encoding != null) {
            servletRequest.setCharacterEncoding(this.encoding);
            servletResponse.setCharacterEncoding(this.encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
