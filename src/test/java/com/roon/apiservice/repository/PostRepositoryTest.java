package com.roon.apiservice.repository;

import com.roon.apiservice.entity.Member;
import com.roon.apiservice.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시물번호로_조회_Test() {
        Optional<Post> post = postRepository.findById(1L);
        System.out.println(post.get());
    }

    @Test
    public void 작성자의_모든_게시물_조회_Test() {
        Member writer = Member.builder()
                .email("user90@naver.com")
                .build();

        List<Post> posts = postRepository.findAllByWriter(writer);
        for (Post post : posts) {
            System.out.println(post);
        }
    }

}