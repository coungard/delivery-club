package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.AUTH_TAG;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = AUTH_TAG)
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @ApiOperation(value = "Sing In user")
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

  @ApiOperation(value = "Sign Up user")
  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public Long signUpUser(@RequestBody SignUpRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
  }

  @ApiOperation(value = "Create a courier")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create-courier")
  @ResponseStatus(HttpStatus.CREATED)
  public Long createCourier(@RequestBody SignUpRequest signUpRequest) {
    return authService.registerCourier(signUpRequest);
  }
}
