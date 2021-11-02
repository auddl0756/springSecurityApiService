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

//리소스를 요청할 때 거치는 필터.
//로그인 한 뒤의 상황이 이 필터를 올바르게 통과하는 시나리오.

@Log4j2
public class AuthCheckFilter extends OncePerRequestFilter {
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private String pattern;

    private static final String AUTH_HEADER_VALUE = "12345678";

    public AuthCheckFilter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (antPathMatcher.match(pattern, request.getRequestURI())) {
            log.info("============== auth check filter ===============");

            boolean isValidHeader = checkHttpAuthHeader(request);
            if (isValidHeader) {
                filterChain.doFilter(request, response);
                return;
            } else {
                sendErrorToken(response);
            }

            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean checkHttpAuthHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader)) {
            log.info("authorization exist : " + authHeader);
            if (authHeader.equals(AUTH_HEADER_VALUE)) {
                return true;
            }
        }

        return false;
    }

    private void sendErrorToken(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");

        JSONObject json = new JSONObject();
        String message = "Authorization header value가 틀려요.";
        json.put("code", "403");
        json.put("message", message);

        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
    }
}
