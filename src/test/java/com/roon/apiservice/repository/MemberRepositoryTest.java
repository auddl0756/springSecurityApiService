package com.roon.apiservice.repository;

import com.roon.apiservice.entity.Member;
import com.roon.apiservice.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //테스트라 보긴 어렵고, 더미데이터 추가하는 용도.
    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(idx -> {
            Member member = Member.builder()
                    .email("user" + idx + "@naver.com")
                    .name("user" + idx)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            if (idx < 50) {
                member.setMemberRole(MemberRole.GUEST);
            } else if (idx < 95) {
                member.setMemberRole(MemberRole.MEMBER);
            } else {
                member.setMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }

    @Test
    public void 이메일로_멤버조회_Test() {
        final String sampleEmail = "user1@naver.com";
        Member member = memberRepository.findByEmail(sampleEmail);
        System.out.println(member);
    }

}