package com.uncodigo.blogspringapi.payload;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Date createAt;
    private Date updateAt;
}
