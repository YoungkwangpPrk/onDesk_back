package com.com4in.itsm.config;

import com.com4in.itsm.jwt.JwtAccessDeniedHandler;
import com.com4in.itsm.jwt.JwtAuthenticationEntryPoint;
import com.com4in.itsm.jwt.JwtFilter;
import com.com4in.itsm.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.swing.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //통큰 생성
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/api/user/authenticate",
            "/api/main/signup",
            "/api/main/apihello",
            "/api/user/changePassword",
            "/api/saml/**",
            "/api/issue/mailTest",
            "/api/hello"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .csrf().disable().cors()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 허용 제한 설정
                .and()
                .authorizeRequests() //권한요청
                .antMatchers(AUTH_WHITE_LIST).permitAll()// 허용 가능 path등록
                .anyRequest().authenticated() // 나머지 접속 불가

                //로그인 화면
                .and()
                .httpBasic()

                // 로그인 팝업 아닌 화면에 표시
                .and()
                .formLogin()

                //세션제어
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                // 토큰을 통한 세션 접근 제어
                .addFilterBefore(//http 검증을 걸처 로그인이 뜨그전에 실행하는 method
                        new JwtFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();

    }
}
