package com.example.course_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // إلغاء حماية CSRF لأنها غير ضرورية في REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()  // المسارات المفتوحة (مثل login/register)
                        .anyRequest().authenticated()                 // باقي المسارات تحتاج توكن
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // ربط فلتر JWT قبل فلتر Spring الأساسي
                .build();
    }
}
