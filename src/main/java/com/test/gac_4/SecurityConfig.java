package com.test.gac_4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // ✅ explicit matcher
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())  // H2 console doesn’t support CSRF
                .headers(headers -> headers.frameOptions().disable()); // allow frames

        return http.build();
    }
}