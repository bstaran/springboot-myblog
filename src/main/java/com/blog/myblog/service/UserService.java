package com.blog.myblog.service;

import com.blog.myblog.repository.UserRepositry;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositry userRepositry;

    public UserService(UserRepositry userRepositry) {
        this.userRepositry = userRepositry;
    }
}
