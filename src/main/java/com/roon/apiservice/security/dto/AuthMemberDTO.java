package com.roon.apiservice.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// 스프링 시큐리티에서 회원이나 계정에 대해서 User라는 키워드를 사용한다.
public class AuthMemberDTO extends User {
    private String email;
    private String name;


    // 스프링 시큐리티에서 username이라는 단어는 회원을 구별할 수 있는 식별 데이터를 의미한다고 한다.
    public AuthMemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
    }
}
