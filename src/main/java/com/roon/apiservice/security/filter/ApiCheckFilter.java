package com.roon.apiservice.security.filter;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
    private AntPathMatcher antPathMatcher;
    private String pattern;

    public ApiCheckFilter(String pattern) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (antPathMatcher.match(pattern, request.getRequestURI())) {
            log.info("=========ApiCheckFilter==========");

            boolean checkHeader = checkAuthHeader(request);
            if (checkHeader) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=utf-8");

                JSONObject json = new JSONObject();
                String message = "API AUTH 틀림";
                json.put("code", "403");
                json.put("message", message);

                PrintWriter printWriter = response.getWriter();
                printWriter.print(json);
            }
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static final String AUTH_NUMBER = "12345678";

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader)) {
            log.info("Authorization 존재 : " + authHeader);
            if (authHeader.equals(AUTH_NUMBER)) {
                checkResult = true;
            }
        }

        return checkResult;
    }
}
