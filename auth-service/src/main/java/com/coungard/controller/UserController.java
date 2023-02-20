package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.USER_TAG;

import com.coungard.security.UserPrincipal;
import com.coungard.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = USER_TAG)
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @ApiOperation(value = "Getting all users")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public List<UserPrincipal> getAllUser() {
    return userService.getAllUsers();
  }

  @GetMapping("/email/{email}")
  public UserPrincipal getUserByEmail(@PathVariable String email) {
    return userService.getUserByEmail(email);
  }
}
