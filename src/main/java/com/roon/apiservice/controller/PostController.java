package com.roon.apiservice.controller;

import com.roon.apiservice.dto.PostRequestDTO;
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
    public void write(@RequestBody PostRequestDTO dto) {  //@RequestBody없어도 될 듯?
        postService.write(dto);
    }

    @GetMapping("/post/{id}")
    public Post getOne(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<Post> getList(@RequestParam String email) {
        return postService.getPostsByEmail(email);
    }

    @PutMapping("/post")
    public void modify(PostRequestDTO dto) {
        postService.modify(dto);
    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable long id) {
        postService.remove(id);
    }
}
