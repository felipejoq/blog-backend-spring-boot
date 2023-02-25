package com.uncodigo.blogspringapi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncodigo.blogspringapi.entity.Category;
import com.uncodigo.blogspringapi.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    @NotEmpty(message = "Post title should not empty")
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String title;
    private String slug;
    @NotEmpty(message = "Post description should not empty")
    @Size(min = 25, message = "Post description should have at least 25 characters")
    private String description;
    @NotEmpty(message = "Post content should not empty")
    private String content;
    private Set<CommentDto> comments;
    private Long categoryId;
    private Long userId;
    @JsonProperty(value = "created_at")
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    private Date updatedAt;
}
