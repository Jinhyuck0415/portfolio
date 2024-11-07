package org.zerock.mmh.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean 더이상 사용하지 않는다.
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user1")
//                .password(passwordEncoder().encode("1111"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("-----------------------filterChain---------------------");
        http.authorizeHttpRequests((auth) -> {
            //CSS를 제대로 출력하기 위함
            auth.requestMatchers("/static/**").permitAll();
            auth.requestMatchers("/assets/**").permitAll();
            auth.requestMatchers("/display/**").permitAll();

            //상품관리
            auth.requestMatchers("/product/**").hasAnyRole("ADMIN", "MANUFACTURER");

            //업체관리
            auth.requestMatchers("/manuinfo/**").hasAnyRole("ADMIN", "MANUFACTURER");

            //업체별 상품관리
            auth.requestMatchers("/mproduct/**").hasAnyRole("ADMIN", "MANUFACTURER");

            //서비스 관리
            auth.requestMatchers("/service/**").hasAnyRole("ADMIN", "MANUFACTURER");
            auth.requestMatchers("/currentProductInfo").hasAnyRole("ADMIN", "MANUFACTURER");

            //옵션 관리
            auth.requestMatchers("/option/**").hasAnyRole("ADMIN", "MANUFACTURER");

            //모두가 읽을 수 있는 페이지
            auth.requestMatchers("/","/login/**","/index.html").permitAll();
            auth.requestMatchers("/user/register").permitAll();
            auth.requestMatchers("/user/joinSuccess").permitAll();
            auth.requestMatchers("/qnaboard/**").permitAll();

            //api 및 rest 관리
            auth.requestMatchers("/api/**").hasAnyRole("ADMIN", "MANUFACTURER");
            auth.requestMatchers("/autoServiceInfo").hasAnyRole("ADMIN", "MANUFACTURER");
            auth.requestMatchers("/autoProductInfo").hasAnyRole("ADMIN", "MANUFACTURER");

            //테스트 페이지
            auth.requestMatchers("/sample/all").permitAll();
            auth.requestMatchers("/uploadEx").permitAll();
            auth.requestMatchers("/search_onclick/**").permitAll();
            auth.requestMatchers("/sample/member").hasAnyRole("USER", "ADMIN", "MANUFACTURER");
        });
        http.formLogin((formLogin) -> formLogin.loginProcessingUrl("/login"));
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(logout -> {
            logout.logoutSuccessUrl("/login")
                    .invalidateHttpSession(true);
        });
        return http.build();
    }
}
