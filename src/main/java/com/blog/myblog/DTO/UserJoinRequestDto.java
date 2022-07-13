package com.blog.myblog.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserJoinRequestDto {
    private String username;
    private String password;
    private String nickname;
    private String email;

    @Override
    public String toString() {
        return "UserJoinRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
