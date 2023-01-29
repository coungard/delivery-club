package com.coungard.controller;

import com.coungard.model.RoleName;
import com.coungard.request.SignInRequest;
import com.coungard.request.SignUpRequest;
import com.coungard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public void login(SignInRequest request) {
    // todo
  }

  @PostMapping("/sign-up")
  public void signUpUser(SignUpRequest request) {
    userService.createUser(request, RoleName.USER);
  }

  @PostMapping("/create-courier")
  public void createCourier(SignUpRequest request) {
    userService.createUser(request, RoleName.COURIER);
  }
}
