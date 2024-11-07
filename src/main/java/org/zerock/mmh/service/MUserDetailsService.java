package org.zerock.mmh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.UserAuthMemberDTO;
import org.zerock.mmh.entity.UserMember;
import org.zerock.mmh.repository.UserMemberRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MUserDetailsService implements UserDetailsService {

    private final UserMemberRepository userMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MUserDetailsService loadUserByUsername " + username);

        Optional<UserMember> result = userMemberRepository.findByUser_mem_id(username);
        if (!result.isPresent()) {
            throw new UsernameNotFoundException("아이디를 다시 확인해주세요.");
        }
        UserMember userMember = result.get();

        log.info("--------------------------------------");
        log.info(userMember);
        log.info(userMember.getPassword());

        UserAuthMemberDTO userAuthMember = new UserAuthMemberDTO(
                userMember.getUser_mem_id(),
                userMember.getPassword(),
                userMember.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet())
        );
        userAuthMember.setUserMemNo(userMember.getUserMemNo());
        userAuthMember.setUser_mem_mail(userMember.getUser_mem_mail());
        userAuthMember.setUser_mem_name(userMember.getUser_mem_name());
        userAuthMember.setUser_mem_address(userMember.getUser_mem_address());
        userAuthMember.setUser_mem_phone(userMember.getUser_mem_phone());
        userAuthMember.setUser_mem_age(userMember.getUser_mem_age());
        userAuthMember.setUser_mem_gender(userMember.getUser_mem_gender());


        return userAuthMember;
    }
}
