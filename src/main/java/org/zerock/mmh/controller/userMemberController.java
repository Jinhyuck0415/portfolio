package org.zerock.mmh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zerock.mmh.dto.UserMemberDTO;
import org.zerock.mmh.entity.UserMember;
import org.zerock.mmh.service.UserMemberService;

@Controller
@RequestMapping("/userMemberController")
@Log4j2
@RequiredArgsConstructor
public class userMemberController {
    private final UserMemberService userMemberService;

    @GetMapping("/userMember")
    public String userMember() {
        return "userMember/userMember";
    }

    @GetMapping("/register")
    public String createMemberForm() {
        return "/userMember/register";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/userMember/loginForm";
    }

    @GetMapping("/joinSuccess")
    public String joinSuccess() {
        return "/userMember/joinSuccess";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserMemberDTO userMemberDTO) {
        try {
            // 회원 가입 서비스 호출
            String userMemNo = userMemberService.join(userMemberDTO);
            log.info("User registered successfully: {}", userMemNo);
            return "redirect:/userMemberController/joinSuccess";
        } catch (IllegalArgumentException e) {
            // 이메일 형식이 잘못된 경우 에러 로그 출력 및 다시 회원 가입 폼으로 리다이렉트
            log.error("Error occurred while registering user: {}", e.getMessage());
            return "redirect:/userMemberController/register?error=true"; // 에러 파라미터 추가
        }
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserMemberDTO.LoginDTO loginDTO) {
        try {
            UserMember userMember = userMemberService.login(loginDTO);
            log.info("User logged in successfully: {}", userMember.getUser_mem_id());
            return "redirect:/userMember/userMember"; //
        } catch (IllegalArgumentException e) {
            log.error("Error occurred during login: {}", e.getMessage());
            return "redirect:/userMemberController/loginForm?error=true"; // 에러 파라미터 추가
        }
    }
}
