package com.hassnain.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hassnain.core.exception.ExceptionResponse;
import com.hassnain.userservice.exception.auth.AuthException;
import com.hassnain.userservice.exception.auth.AuthExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;


public class AuthenticationExceptionHandler extends OncePerRequestFilter {
    final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionHandler.class.getName());


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try{
            filterChain.doFilter(request,response);
        }catch(AuthException.InvalidCredentialException  | IOException  exception){
            logger.error(exception.getMessage());
            ExceptionResponse res = new ExceptionResponse();
            res.setMessage(AuthExceptionType.INVALID_CREDENTIAL.getMessage());
            res.setAction(AuthExceptionType.INVALID_CREDENTIAL.getAction());

            String s = new ObjectMapper().writeValueAsString(res);
            response.setStatus(AuthExceptionType.INVALID_CREDENTIAL.getHttpStatus().value());
            response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(s);
            response.flushBuffer();

        }
        catch (AuthException.UserLockedException exception){
            ExceptionResponse res = new ExceptionResponse();
            String message = MessageFormat.format(AuthExceptionType.USER_LOCKED.getMessage(), exception.getMessage()).trim();
            res.setMessage(message);
            res.setAction(AuthExceptionType.USER_LOCKED.getAction());

            String s = new ObjectMapper().writeValueAsString(res);
            response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(AuthExceptionType.USER_LOCKED.getHttpStatus().value());
            response.getWriter().write(s);
            response.flushBuffer();
        }

    }


}
