package com.example.springdemo1.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class RequestResponseLogingFilter implements Filter {
    private static final Logger LOG =LoggerFactory.getLogger(RequestResponseLogingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("init filter {}",this);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        filterChain.doFilter(servletRequest,servletResponse);
        LOG.info("Logging Response :{}", res.getContentType());
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}
