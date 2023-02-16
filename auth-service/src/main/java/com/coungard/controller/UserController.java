package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.USER_TAG;

import com.coungard.security.UserPrincipal;
import com.coungard.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = USER_TAG)
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @ApiOperation(value = "Getting all users")
  @GetMapping("/all")
  public List<UserPrincipal> getAllUser() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    System.out.println(userPrincipal.getUsername());
    return userService.getAllUsers();
  }
}
