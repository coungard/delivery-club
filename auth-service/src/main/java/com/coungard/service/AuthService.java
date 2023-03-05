package com.coungard.service;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;

public interface AuthService {

  AuthenticationResponse authenticateUser(LoginRequest loginRequest);

  AuthenticationResponse registerUser(SignUpRequest request);

  AuthenticationResponse registerCourier(SignUpRequest request);
}
