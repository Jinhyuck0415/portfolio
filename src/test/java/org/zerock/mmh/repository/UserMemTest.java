package org.zerock.mmh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.mmh.entity.MemberRole;
import org.zerock.mmh.entity.UserMember;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class UserMemTest {
    @Autowired
    private UserMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummy() {
        IntStream.rangeClosed(16, 20).forEach(i -> {
            UserMember member = UserMember.builder()
                    .user_mem_id("user" + i)
                    .password(passwordEncoder.encode("1234"))
                    .user_mem_mail("user" + i + "@test.test")
                    .user_mem_name("사용자" + i)
                    .user_mem_address("주소" + i)
                    .user_mem_phone("000-0000-000" + i)
                    .user_mem_age(20)
                    .user_mem_gender('m')
                    .build();

            //default role
           // member.addMemberRole(MemberRole.USER);

         //   member.addMemberRole(MemberRole.MANUFACTURER);

           member.addMemberRole(MemberRole.ADMIN);

            repository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<UserMember> result = repository.findByUser_mem_id("user1");
        UserMember member = result.get();
        System.out.println(member);
    }
}
