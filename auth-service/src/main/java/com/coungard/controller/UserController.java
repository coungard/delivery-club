package com.coungard.controller;

import com.coungard.security.UserPrincipal;
import com.coungard.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public List<UserPrincipal> getAllUser() {
    return userService.getAllUsers();
  }
}
