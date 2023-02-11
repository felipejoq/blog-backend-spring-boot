package com.uncodigo.blogspringapi.payload;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String author;
    private String email;
    private String body;
    private Date createAt;
    private Date updateAt;
}
