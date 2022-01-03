package com.roon.apiservice.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 특정한 URL로 외부에서 로그인이 가능하도록 하고,
// 로그인이 성공하면 클라이언트가 Authorization 헤더의 값으로 이용할 데이터를 전송하는 역할.

// API를 사용하기 위해서 별도의 메뉴로 승인을 받는 것이 일반적이다..? 별도의 메뉴가 뭐 말하는거지?
// 이 예제에서는 로그인과 동일한 계정으로 로그인하면 일정 기간 동안 API를 호출할 수 있도록 구성한다고 함...
@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {
    public ApiLoginFilter(String defaultFilterProcessUrl){
        super(defaultFilterProcessUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        log.info("ApiLoginFilter....");

        String email = httpServletRequest.getParameter("email");
        String password = "1111";

        if(email == null){
            throw new BadCredentialsException("email cannot be null.");
        }

        return null;
    }
}
