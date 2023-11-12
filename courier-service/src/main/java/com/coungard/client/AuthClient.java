package com.coungard.client;

import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;
import com.coungard.security.UserPrincipal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface AuthClient {

  @GetMapping("/user/email/{email}")
  UserPrincipal getUserByEmail(@PathVariable("email") String email);

  @PostMapping("/auth/create-courier")
  AuthenticationResponse registerCourier(@RequestBody SignUpRequest signUpRequest, @RequestHeader("Authorization") String jwtToken);
}
