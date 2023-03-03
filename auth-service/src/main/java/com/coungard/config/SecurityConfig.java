package com.coungard.config;

import com.coungard.security.CustomUserDetailsService;
import com.coungard.security.MyBasicAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

  private static final String[] AUTH_WHITELIST = {
      "/swagger-resources/**",
      "/swagger-ui/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**",
      "/h2/**",
      "/"
  };

  private final CustomUserDetailsService userDetailsService;
  private final MyBasicAuthenticationEntryPoint authenticationEntryPoint;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
        AuthenticationManagerBuilder.class);

    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());

    return authenticationManagerBuilder.build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .mvcMatchers("/auth/login", "/auth/sign-up", "/auth/test", "/user/**").permitAll()
        .mvcMatchers(AUTH_WHITELIST).permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(authenticationEntryPoint);

    return http.build();
  }
}
