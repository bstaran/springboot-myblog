package com.blog.myblog.Controller;

import com.blog.myblog.DTO.UserJoinRequestDto;
import com.blog.myblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String join(UserJoinRequestDto userJoinRequestDto) {
        System.out.println(userJoinRequestDto.toString());
        return "redirect:/user/login";
    }
}
