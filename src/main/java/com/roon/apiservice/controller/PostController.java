package com.roon.apiservice.controller;

import com.roon.apiservice.entity.Post;
import com.roon.apiservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public void write(@RequestBody Post post){  //@RequestBody없어도 될 듯?
        postService.write(post);
    }

    @GetMapping("/post/{id}")
    public Post getOne(@PathVariable long id){
        return postService.getPostById(id);
    }

    @GetMapping("/{email}")
    public List<Post> getList(String email){
        return postService.getPostsByEmail(email);
    }

    @PutMapping("/post")
    public void modify(Post post){
        postService.modify(post);
    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable long id){
        postService.remove(id);
    }
}
