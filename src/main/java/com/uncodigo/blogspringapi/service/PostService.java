package com.uncodigo.blogspringapi.service;

import com.uncodigo.blogspringapi.payload.PostDto;
import com.uncodigo.blogspringapi.payload.PostResponse;


public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePostById(PostDto postDto, Long id);
    PostDto deletePostById(Long id);
}
