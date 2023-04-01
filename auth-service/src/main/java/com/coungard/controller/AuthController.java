package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.AUTH_TAG;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;
import com.coungard.model.response.DetailedAuthenticationResponse;
import com.coungard.security.UserPrincipal;
import com.coungard.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
  public ResponseEntity<DetailedAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.authenticateUser(loginRequest));
  }

  @ApiOperation(value = "Sign Up user")
  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AuthenticationResponse> signUpUser(@RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.ok(authService.registerUser(signUpRequest));
  }

  @ApiOperation(value = "Create a courier by Admin")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create-courier")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AuthenticationResponse> createCourier(@RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.ok(authService.registerCourier(signUpRequest));
  }

  @ApiOperation(value = "Identification user by token")
  @GetMapping("/user/me")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<UserPrincipal> identify(@RequestHeader(HttpHeaders.AUTHORIZATION)  String authHeader) {
    return ResponseEntity.ok(authService.identify(authHeader));
  }
}
