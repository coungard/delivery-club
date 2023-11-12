package com.coungard.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class JwtTokenInterceptor implements RequestInterceptor {

  @Value("${security.jwt.token}")
  private String jwtToken;

  @Override
  public void apply(RequestTemplate template) {
    template.header("Authorization", "Bearer " + jwtToken);
  }
}