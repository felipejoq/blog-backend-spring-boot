package com.uncodigo.blogspringapi.service.impl;

import com.uncodigo.blogspringapi.entity.Category;
import com.uncodigo.blogspringapi.entity.Post;
import com.uncodigo.blogspringapi.entity.User;
import com.uncodigo.blogspringapi.exception.ResourceNotFoundException;
import com.uncodigo.blogspringapi.payload.PostDto;
import com.uncodigo.blogspringapi.payload.PostResponse;
import com.uncodigo.blogspringapi.repository.CategoryRepository;
import com.uncodigo.blogspringapi.repository.PostRepository;
import com.uncodigo.blogspringapi.repository.UserRepository;
import com.uncodigo.blogspringapi.service.PostService;
import com.uncodigo.blogspringapi.utils.GenSlug;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper mapper,
                           CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", postDto.getUserId().toString()));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postDto.getCategoryId().toString()));

        // Convert Dto to Entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        post.setUser(user);
        // Persist entity
        Post newPost = postRepository.save(post);

        // Convert Entity to Dto.
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", postDto.getUserId().toString()));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postDto.getCategoryId().toString()));

        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setUser(user);
        post.setCategory(category);

        Post updatepost = postRepository.save(post);

        return mapToDto(updatepost);
    }

    @Override
    public PostDto deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));
        postRepository.delete(post);
        return mapToDto(post);
    }

    @Override
    public List<PostDto> getPostByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId.toString()));

        List<Post> posts = postRepository.findByCategoryId(category.getId());

        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // convert Entity into Dto with ModelMapper library
    private PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);
    }

    // convert Dto into Entity with ModelMapper library
    private Post mapToEntity(PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }

}
