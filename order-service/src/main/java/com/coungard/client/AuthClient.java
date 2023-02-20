package com.coungard.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", path = "/user")
public interface AuthClient {

  @GetMapping("/email/{email}")
  UserDetails getUserByEmail(@PathVariable String email);
}
