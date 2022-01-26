package com.hassnain.userservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hassnain.core.security.jwt.JwtManager;
import com.hassnain.core.security.jwt.JwtTokenPayloadKey;
import com.hassnain.core.security.jwt.JwtTokenRequest;
import com.hassnain.userservice.config.CustomBeanProvider;
import com.hassnain.userservice.dto.LoginRequest;
import com.hassnain.userservice.entity.User;
import com.hassnain.userservice.exception.auth.AuthException;
import com.hassnain.userservice.exception.user.UserException;
import com.hassnain.userservice.mapper.UserMapper;
import com.hassnain.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;


public class ApiUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    final Logger logger = LoggerFactory.getLogger(ApiUsernamePasswordAuthFilter.class.getName());

//    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;
    private final UserService userService;
    private final UserMapper userMapper;

    public ApiUsernamePasswordAuthFilter(AuthenticationManager authenticationManager) {

//        this.authenticationManager = authenticationManager;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/users/login", HttpMethod.POST.name()));
        this.jwtManager = CustomBeanProvider.getBean(JwtManager.class);
        this.userService = CustomBeanProvider.getBean(UserService.class);
        this.userMapper = CustomBeanProvider.getBean(UserMapper.class);
        super.setUsernameParameter("email");
        super.setPasswordParameter("password");
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginRequest loginRequest = null;
        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            logger.info("Received login Request from User: "+loginRequest.getEmail());
            return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        } catch(IOException | BadCredentialsException e){
            throw new AuthException.InvalidCredentialException();
        } catch (LockedException e){
            throw new AuthException.UserLockedException(loginRequest != null ? loginRequest.getEmail() : "");
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = authResult.getName();

        User userByEmail = userService.findUserByEmail(username);

        JwtTokenRequest tokenRequest = new JwtTokenRequest(username,
                Map.of(
                        JwtTokenPayloadKey.AUTHORITIES.getKey(),
                        List.of(userByEmail.getUserType().getName())
                )
        );
        String token = jwtManager.createToken(tokenRequest);
        String json = new ObjectMapper().writeValueAsString(userMapper.UserToLoginUserReponse(userByEmail,token));
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
        response.flushBuffer();

    }
}
