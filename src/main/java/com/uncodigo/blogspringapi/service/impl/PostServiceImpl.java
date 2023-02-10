package com.uncodigo.blogspringapi.service.impl;

import com.uncodigo.blogspringapi.entity.Post;
import com.uncodigo.blogspringapi.exception.ResourceNotFoundException;
import com.uncodigo.blogspringapi.payload.PostDto;
import com.uncodigo.blogspringapi.repository.PostRepository;
import com.uncodigo.blogspringapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // Convert Dto to Entity
        Post post = mapToEntity(postDto);

        // Persist entity
        Post newPost = postRepository.save(post);

        // Convert Entity to Dto.
        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatepost = postRepository.save(post);

        return mapToDto(updatepost);
    }

    @Override
    public PostDto deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));
        postRepository.delete(post);
        return mapToDto(post);
    }

    // convert Entity into Dto
    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert Dto into Entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
