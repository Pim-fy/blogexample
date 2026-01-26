// 스프링 시큐리티 구현하며 작성한 설정 비활성화(전체 주석처리)
// OAuth2와 JWT를 사용하기 위함.
//package me.hwanghj.config;
//
//import lombok.RequiredArgsConstructor;
//import me.hwanghj.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor        // final 필드 생성자 자동 생성.
//public class WebSecurityConfig {
//    private final UserDetailService userService;
//
//    // 스프링 시큐리티 기능 비활성화.
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers(new AntPathRequestMatcher("/static/**"));
//    }
//
//    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests(auth -> auth.requestMatchers(
//                        new AntPathRequestMatcher("/login"),
//                        new AntPathRequestMatcher("/signup"),
//                        new AntPathRequestMatcher("/user")
//                ).permitAll().anyRequest().authenticated())
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")                // 로그인 페이지 경로 설정.
//                        .defaultSuccessUrl("/articles")     // 로그인 완료시 이동할 페이지 설정.
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")         // 로그아웃 완료시 이동할 페이지 설정.
//                        .invalidateHttpSession(true)        // 로그아웃 이후 세션 전체 삭제할지 여부 설정.
//                )
//                .csrf(AbstractHttpConfigurer::disable)      // csrf 비활성화.
//                .build();
//    }
//
//    // 인증 관리자 관련 설정.
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userService) throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);            // 사용자 정보 서비스 설정
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return new ProviderManager(authProvider);
//    }
//
//    // 패스워드 인코더로 사용할 빈 등록.
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
