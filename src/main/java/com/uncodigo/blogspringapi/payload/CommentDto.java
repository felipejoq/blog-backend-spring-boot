package com.uncodigo.blogspringapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Author should not be null or empty")
    private String author;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email format is incorrect")
    private String email;

    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;

    private Date createAt;

    private Date updateAt;
}
