package com.roon.apiservice.security.filter;

import com.roon.apiservice.entity.Member;
import com.roon.apiservice.security.dto.AuthMemberDTO;
import com.roon.apiservice.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {
    private JWTUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        log.info("==============ApiLoginFilter==============");

        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("authResult = " + authResult);
        log.info(authResult.getPrincipal());

        String email = ((AuthMemberDTO) authResult.getPrincipal()).getUsername();

        String token = null;
        try {
            token = jwtUtil.generateToken(email);

            response.setContentType("text/plain");
            response.getOutputStream().write(token.getBytes(StandardCharsets.UTF_8));

            log.info(token);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
