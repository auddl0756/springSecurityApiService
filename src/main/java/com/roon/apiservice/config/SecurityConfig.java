package com.roon.apiservice.config;

import com.roon.apiservice.entity.MemberRole;
import com.roon.apiservice.security.filter.ApiCheckFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //필터 bean으로 등록
    @Bean
    ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/posts/**/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/add").permitAll()
                .antMatchers("/sample/member").hasRole(MemberRole.MEMBER.name());

        http.formLogin();
        http.csrf().disable();
        http.logout();

        //필터 순서 조정
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class); //.class는 어떤 의미지

    }
}
