package com.coungard.service;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;
import com.coungard.model.response.DetailedAuthenticationResponse;
import com.coungard.model.response.PrincipalResponse;

public interface AuthService {

  DetailedAuthenticationResponse authenticateUser(LoginRequest loginRequest);

  AuthenticationResponse registerUser(SignUpRequest request);

  AuthenticationResponse registerCourier(SignUpRequest request);

  PrincipalResponse identify(String authHeader);
}
