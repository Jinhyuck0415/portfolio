package org.zerock.mmh.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMemberDTO {

    private String userMemNo;

    @NotNull
    private String userMemId;

    @NotNull
    private String userMemPw;

    @NotNull
    private String userMemMail; // 기본 이메일 아이디

    @NotNull
    private String userMemName;

    @NotNull
    private String userMemAddress;

    @NotNull
    private String userMemPhone;

    private int userMemAge;

    @NotNull
    private char userMemGender;

    private String userMemMailFull; // 조합된 이메일
    private String userMemMailSelect; // 선택된 도메인
    private String userMemMailDirect; // 직접 입력된 이메일 부분

    public class LoginDTO {
        @NotNull
        private String userMemId;

        @NotNull
        private String userMemPw;
    }
}
