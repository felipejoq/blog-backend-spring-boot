package com.uncodigo.blogspringapi.controller;

import com.uncodigo.blogspringapi.payload.PostDto;
import com.uncodigo.blogspringapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all blog post
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }



}
