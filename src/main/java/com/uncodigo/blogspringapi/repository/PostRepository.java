package com.uncodigo.blogspringapi.repository;

import com.uncodigo.blogspringapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
