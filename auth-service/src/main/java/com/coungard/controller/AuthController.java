package com.coungard.controller;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public void login(LoginRequest loginRequest) {
    authService.authenticateUser(loginRequest);
  }

  @PostMapping("/sign-up")
  public void signUpUser(SignUpRequest signUpRequest) {
    authService.registerUser(signUpRequest);
  }

  @PostMapping("/create-courier")
  public void createCourier(SignUpRequest signUpRequest) {
    authService.registerCourier(signUpRequest);
  }
}
