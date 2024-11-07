package org.zerock.mmh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMemberDTO {
    private String userMemNo;
    private String user_mem_id;
    private String password;
    private String user_mem_mail;
    private String user_mem_name;
    private String user_mem_address;
    private String user_mem_phone;
    private int user_mem_age;
    private char user_mem_gender;
}
