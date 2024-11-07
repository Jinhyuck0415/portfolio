package org.zerock.mmh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.UserMemberDTO;
import org.zerock.mmh.entity.MemberRole;
import org.zerock.mmh.entity.UserMember;
import org.zerock.mmh.repository.UserMemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserMemberServiceImpl implements UserMemberService {

    private final UserMemberRepository repository;
    private final  PasswordEncoder passwordEncoder;

    @Override
    public String register(UserMemberDTO dto){
        UserMember entity = dtoToEntity(dto);
        entity.addMemberRole(MemberRole.USER);
        repository.save(entity);
        return entity.getUserMemNo();
    }

    public UserMember dtoToEntity(UserMemberDTO dto) {
        UserMember entity = UserMember.builder()
                .userMemNo(dto.getUserMemNo())
                .user_mem_id(dto.getUser_mem_id())
                .password(passwordEncoder.encode(dto.getPassword()))
                .user_mem_mail(dto.getUser_mem_mail())
                .user_mem_name(dto.getUser_mem_name())
                .user_mem_address(dto.getUser_mem_address())
                .user_mem_phone(dto.getUser_mem_phone())
                .user_mem_age(dto.getUser_mem_age())
                .user_mem_gender(dto.getUser_mem_gender())
                .build();
        return entity;
    }
}
