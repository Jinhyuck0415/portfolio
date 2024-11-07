package org.zerock.mmh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.mmh.dto.UserMemberDTO;
import org.zerock.mmh.entity.UserMember;

public interface UserMemberService {



    String register(UserMemberDTO userMemberDTO);


}
