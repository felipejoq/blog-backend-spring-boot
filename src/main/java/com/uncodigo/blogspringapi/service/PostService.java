package com.uncodigo.blogspringapi.service;

import com.uncodigo.blogspringapi.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePostById(PostDto postDto, Long id);
    PostDto deletePostById(Long id);
}
