package org.zerock.mmh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.mmh.dto.UserMemberDTO;
import org.zerock.mmh.service.UserMemberService;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserMemberController {

    private final UserMemberService service;

    @GetMapping("/register")
    public void register() {
        log.info("register...........");
    }

    @PostMapping("/register")
    public String registerMember(UserMemberDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..........."+dto);

        String UserMemNo = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", UserMemNo);
        return "redirect:/user/joinSuccess";
//        try {
//            // 회원 가입 서비스 호출
//            String userMemNo = service.register(dto);
//            log.info("User registered successfully: {}", userMemNo);
//            return "redirect:/user/joinSuccess";
//        } catch (IllegalArgumentException e) {
//            // 이메일 형식이 잘못된 경우 에러 로그 출력 및 다시 회원 가입 폼으로 리다이렉트
//            log.error("Error occurred while registering user: {}", e.getMessage());
//            return "redirect:/user/register?error=true"; // 에러 파라미터 추가
//        }
    }

    @GetMapping("/joinSuccess")
    public String joinSuccess() {
        return "/user/joinSuccess";
    }
}
