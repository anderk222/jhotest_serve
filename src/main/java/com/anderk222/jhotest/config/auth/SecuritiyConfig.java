package com.anderk222.jhotest.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.anderk222.jhotest.util.TokenUtil;
import static com.anderk222.jhotest.util.PropertiesConverter.JWT_RANDOM_KEY;

import javax.crypto.SecretKey;

// @Configuration
public class SecuritiyConfig {

    @Value(JWT_RANDOM_KEY)
    private SecretKey secretKey;

    @Autowired
    private UserDetailServiceImpl service;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager manager) throws Exception {

        TokenUtil tokenUtil = new TokenUtil(secretKey);

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenUtil);
        jwtAuthenticationFilter.setAuthenticationManager(manager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        http.csrf((c) -> c.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/register")
                        .permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(new JwtAuthorizationFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(service).passwordEncoder(encoder());

        return builder.build();

    }

    @Bean
    BCryptPasswordEncoder encoder() {

        return new BCryptPasswordEncoder();

    }

}
