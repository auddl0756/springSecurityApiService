package com.roon.apiservice.service;

import com.roon.apiservice.dto.PostRequestDTO;
import com.roon.apiservice.entity.Member;
import com.roon.apiservice.entity.Post;
import com.roon.apiservice.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    private static final int TRANSACTION_TIME_LIMIT = 10;

    public Post getPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no post with id = " + id));
    }

    public List<Post> getPostsByEmail(String email) {
        Member writer = Member.builder()
                .email(email)
                .build();

        return postRepository.findAllByWriter(writer);
    }

    @Transactional(timeout = TRANSACTION_TIME_LIMIT)
    public void write(PostRequestDTO dto) {
        //DB에 이미 존재하는 Member를
        //pk인 email 이용해서 객체 build하면
        //jpa의 캐시에 있는거 가져오거나 없으면 DB에 있는거 가져오게 되겠지
        //즉, pk만 넘겨주면 이미 존재하는거 가져올 수 있는거겠지?
        Member writer = Member.builder()
                .email(dto.getEmail())
                .build();

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(writer)
                .build();

        postRepository.save(post);
    }

    @Transactional(timeout = TRANSACTION_TIME_LIMIT)
    public void modify(PostRequestDTO dto) {
        Post post = postRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("no post with id = " + dto.getId()));

        post.changeTitle(dto.getTitle());
        post.changeContent(dto.getContent());
    }

    public void remove(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no post with id = "+ id));

        postRepository.delete(post);
    }
}
