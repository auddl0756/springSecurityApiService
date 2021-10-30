package com.roon.apiservice.security.service;

import com.roon.apiservice.entity.Member;
import com.roon.apiservice.repository.MemberRepository;
import com.roon.apiservice.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Log4j2
@Service
public class MemberAuthService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Member member = memberRepository.findByEmail(username);
        log.info(member);

        if (member == null) {
            throw new UsernameNotFoundException("이메일이 " + username + "인 멤버는 존재하지 않습니다.");
        } else {
            AuthMemberDTO authMemberDTO =
                    new AuthMemberDTO(member.getEmail(),
                            member.getPassword(),
                            Arrays.asList(new SimpleGrantedAuthority("ROLE_" + member.getMemberRole().name()))
                    );

            return authMemberDTO;
        }
    }
}
