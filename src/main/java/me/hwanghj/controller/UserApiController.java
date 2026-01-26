package me.hwanghj.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.hwanghj.dto.AddUserRequest;
import me.hwanghj.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor        // final 필드 생성자 자동 생성.
@Controller
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);      // 회원 가입 메서드 호출.
        return "redirect:/login";       // 회원 가입 완료 후 로그인 페이지로 이동.
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(
                request,
                response,
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );      // 로그아웃을 담당하는 핸들러 SecurityContextLogoutHandler.
        return "redirect:/login";
    }
}
