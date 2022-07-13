package com.blog.myblog.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserJoinRequestDto {
    private String username;
    private String password;
    private String nickname;

    private String role;

    @Override
    public String toString() {
        return super.toString();
    }
}
