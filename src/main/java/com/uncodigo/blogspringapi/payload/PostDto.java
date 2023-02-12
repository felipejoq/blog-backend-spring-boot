package com.uncodigo.blogspringapi.payload;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
    private Date createAt;
    private Date updateAt;
}
