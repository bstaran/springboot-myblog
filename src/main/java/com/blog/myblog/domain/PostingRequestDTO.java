package com.blog.myblog.domain;

import lombok.Getter;

@Getter
public class PostingRequestDTO {
    private String username;
    private String title;
    private String pw;
    private String contents;
}
