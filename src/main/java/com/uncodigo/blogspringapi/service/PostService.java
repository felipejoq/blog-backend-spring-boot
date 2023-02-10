package com.uncodigo.blogspringapi.service;

import com.uncodigo.blogspringapi.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
