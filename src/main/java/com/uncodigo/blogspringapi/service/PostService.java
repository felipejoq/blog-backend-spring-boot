package com.uncodigo.blogspringapi.service;

import com.uncodigo.blogspringapi.payload.PostDto;
import com.uncodigo.blogspringapi.payload.PostResponse;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePostById(PostDto postDto, Long id);
    PostDto deletePostById(Long id);
    List<PostDto> getPostByCategory(Long categoryId);
}
