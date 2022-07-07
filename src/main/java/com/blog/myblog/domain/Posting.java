package com.blog.myblog.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Posting extends Timestamped {
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String contents;

    public Posting(String username, String title, String pw, String contents) {
        this.username = username;
        this.title = title;
        this.pw = pw;
        this.contents = contents;
    }

    public Posting(PostingRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.title = requestDTO.getTitle();
        this.pw = requestDTO.getPw();
        this.contents = requestDTO.getContents();
    }

    public void update(PostingRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.title = requestDTO.getTitle();
        this.contents = requestDTO.getContents();
    }
}
