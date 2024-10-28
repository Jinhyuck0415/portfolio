package org.zerock.mmh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.UserMemberDTO;
import org.zerock.mmh.entity.UserMember;
import org.zerock.mmh.repository.UserMemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserMemberServiceImpl implements UserMemberService {

    private final UserMemberRepository userMemberRepository;

    @Override
    public UserMember saveMember(UserMember userMember) {
        return userMemberRepository.save(userMember);
    }

    @Override
    public String join(UserMemberDTO userMemberDTO) {
        log.info("Joining user with details: {}", userMemberDTO);

        String email = "";

        if (userMemberDTO.getUserMemMailDirect() != null && !userMemberDTO.getUserMemMailDirect().isEmpty()) {
            // 직접 입력된 도메인 처리
            email = userMemberDTO.getUserMemMail() + "@" + userMemberDTO.getUserMemMailDirect(); // '@' 추가
        } else {
            // 선택된 도메인 처리
            if (userMemberDTO.getUserMemMailSelect() == null || userMemberDTO.getUserMemMailSelect().isEmpty() || "none".equals(userMemberDTO.getUserMemMailSelect())) {
                throw new IllegalArgumentException("도메인을 선택하거나 직접 입력하세요.");
            }
            email = userMemberDTO.getUserMemMail() + "@" + userMemberDTO.getUserMemMailSelect();
        }

        // 이메일 형식 확인
        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            log.error("Invalid email format: {}", email);
            throw new IllegalArgumentException("잘못된 이메일 형식입니다.");
        }

        UserMember userMember = UserMember.builder()
                .user_mem_id(userMemberDTO.getUserMemId())
                .user_mem_pw(userMemberDTO.getUserMemPw())
                .user_mem_mail(email) // 조합된 이메일 사용
                .user_mem_name(userMemberDTO.getUserMemName())
                .user_mem_address(userMemberDTO.getUserMemAddress())
                .user_mem_phone(userMemberDTO.getUserMemPhone())
                .user_mem_age(userMemberDTO.getUserMemAge())
                .user_mem_gender(userMemberDTO.getUserMemGender())
                .build();

        return saveMember(userMember).getUserMemNo();
    }

    @Override
    public UserMember login(UserMemberDTO.LoginDTO loginDTO) {
        return null;
    }
}
