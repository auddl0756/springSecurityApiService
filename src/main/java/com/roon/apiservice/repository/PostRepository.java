package com.roon.apiservice.repository;

import com.roon.apiservice.entity.Member;
import com.roon.apiservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    //Post findById(long id);

    List<Post> findAllByWriter(Member writer);
}
