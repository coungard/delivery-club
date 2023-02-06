package com.coungard.controller;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.service.AuthService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public void login(@RequestBody LoginRequest loginRequest, HttpServletResponse response)
      throws IOException {
    boolean authenticated = authService.authenticateUser(loginRequest);
    if (authenticated) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().println("Bad Credentials");
    }
  }

  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public Long signUpUser(@RequestBody SignUpRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create-courier")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createCourier(@RequestBody SignUpRequest signUpRequest) {
    return authService.registerCourier(signUpRequest);
  }
}
