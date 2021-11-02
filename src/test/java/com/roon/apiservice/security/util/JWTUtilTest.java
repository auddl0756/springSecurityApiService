package com.roon.apiservice.security.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JWTUtilTest {
    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore() {
        System.out.println("test before");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncode() throws Exception {
        String email = "user90@naver.com";
        String encodedEmail = jwtUtil.generateToken(email);

        System.out.println(encodedEmail);
    }

    @Test
    public void testValidate() throws Exception{
        String email = "user90@naver.com";
        String encodedEmail = jwtUtil.generateToken(email);

        Thread.sleep(5000);     //expire date test

        String resultEmail = jwtUtil.validateAndExtract(encodedEmail);
        System.out.println(resultEmail);
    }
}