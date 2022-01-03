package com.roon.apiservice.security.filter.test;

import com.sun.deploy.net.HttpResponse;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

// HTTP 요청이 웹 어플리케이션으로 들어오면, 필터가 가로챈다.
// 필터는 request URI, request parameters, request headers 등을 기반으로
// target servlet으로 forward할 지, 못 가게 막을지 결정할 수 있다.

@Log4j2
public class SimpleServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SimpleServletFilter's init()===============================");
        log.info(filterConfig.getFilterName());
        log.info(filterConfig.getServletContext());
        log.info(filterConfig.getInitParameterNames());
    }

    @Override
    public void destroy() {
        log.info("SimpleServletFilter's destroy()===============================");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("SimpleServletFilter's doFilter()===============================");

        String myParam = servletRequest.getParameter("myParam");

        if (myParam.equals("blockTheRequest")) {
            servletResponse.getWriter().write("this param is blocked by filter = " + this.getClass());

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
