package com.blog.myblog.Controller;

import com.blog.myblog.DTO.UserJoinRequestDto;
import com.blog.myblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/userLogin/join")
    public String join() {
        return "join";
    }

    @PostMapping("/userLogin/join")
    public String join(UserJoinRequestDto userJoinRequestDto) {
        System.out.println(userJoinRequestDto.toString());
        userService.registerUser(userJoinRequestDto);
        return "redirect:/login";
    }
}
