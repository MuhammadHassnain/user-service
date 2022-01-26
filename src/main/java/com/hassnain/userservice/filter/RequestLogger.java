package com.hassnain.userservice.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;



public class RequestLogger extends OncePerRequestFilter {

    final Logger logger = LoggerFactory.getLogger(RequestLogger.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Enumeration<String> headerNames = request.getHeaderNames();
        logger.info("Receive Following Header");
        Collections.list(headerNames)
                                .forEach(e -> logger.info(e+":"+request.getHeader(e)));
        filterChain.doFilter(request,response);
    }
}
