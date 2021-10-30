package com.roon.apiservice.service;

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
        //return postRepository.findById(id);
        Optional<Post> result = postRepository.findById(id);
        return result.orElse(null);
    }

    //이메일로만 검색하는 dao 메서드 만들기
    // vs 이메일로 객체 탐색(Mmeber,builder.email(email).build)하고 객체를 dao에게 주는 것
    // 두 방법이 성능 차이 크려나..?
    public List<Post> getPostsByEmail(String email){
        Member writer = Member.builder()
                .email(email)
                .build();

        return postRepository.findAllByWriter(writer);
    }

    // 근데 타임 아웃되면 예외를 처리해야 할텐데?
    @Transactional(timeout = TRANSACTION_TIME_LIMIT)
    public void write(Post post) {
        postRepository.save(post);
    }

    @Transactional(timeout = TRANSACTION_TIME_LIMIT)
    public void modify(Post post) {
        // null 보다 optional 쓰는게 더 나은듯
        // 왜냐면 null로 체크하면 dao 접근 한 번 더 해야 함.
        Optional<Post> result = postRepository.findById(post.getId());

        if (result.isPresent()) {
            Post updatedPost = result.get();
            updatedPost.changeTitle(post.getTitle());
            updatedPost.changeContent(post.getContent());
        } else {
            log.info("해당 글이 존재하지 않는다?");
        }
    }

    public void remove(long id) {
        Post toDeletePost = Post.builder()
                .id(id)
                .build();

        postRepository.delete(toDeletePost);
    }
}
