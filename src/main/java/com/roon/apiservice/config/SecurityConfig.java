package com.roon.apiservice.config;

import com.roon.apiservice.entity.MemberRole;
import com.roon.apiservice.security.filter.AuthCheckFilter;
import com.roon.apiservice.security.filter.LoginFilter;
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

    @Bean
    public AuthCheckFilter authCheckFilter() {
        return new AuthCheckFilter("/posts/**/*");
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter("/api/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/add").permitAll()
                .antMatchers("/sample/member").hasRole(MemberRole.MEMBER.name());

        http.formLogin();
        http.csrf().disable();
        http.logout();

        http.addFilterBefore(authCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
