package com.blog.myblog.service;

import com.blog.myblog.DTO.UserJoinRequestDto;
import com.blog.myblog.model.User;
import com.blog.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserJoinRequestDto userJoinRequestDto) {
        // 회원 ID 중복 확인
        String username = userJoinRequestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        // password 암호화
        String password = passwordEncoder.encode(userJoinRequestDto.getPassword());

        String role = "ROLE_USER";
        String nickname = userJoinRequestDto.getNickname();

        User user = new User(username, password, nickname, role);
        userRepository.save(user);
    }
}
