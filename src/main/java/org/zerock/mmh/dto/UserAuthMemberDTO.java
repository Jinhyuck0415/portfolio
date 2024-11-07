package org.zerock.mmh.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class UserAuthMemberDTO extends User {

    private String userMemNo;
    private String password;
    private String user_mem_id;
    private String user_mem_mail;
    private String user_mem_name;
    private String user_mem_address;
    private String user_mem_phone;
    private int user_mem_age;
    private char user_mem_gender;


    public UserAuthMemberDTO(String username,
                             String password,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user_mem_id = username;
        this.password=password;
    }
}
