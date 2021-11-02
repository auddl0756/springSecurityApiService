package com.roon.apiservice.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class AuthCheckFilter extends OncePerRequestFilter {
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private String pattern;

    public AuthCheckFilter(String pattern){
        this.pattern = pattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(antPathMatcher.match(pattern,request.getRequestURI())){
            log.info("============== auth check filter ===============");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
