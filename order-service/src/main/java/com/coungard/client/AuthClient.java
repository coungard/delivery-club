package com.coungard.client;

import com.coungard.security.UserPrincipal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", path = "/user")
public interface AuthClient {

  @GetMapping("/email/{email}")
  UserPrincipal getUserByEmail(@PathVariable String email);
}
