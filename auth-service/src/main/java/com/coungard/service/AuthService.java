package com.coungard.service;

import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;

public interface AuthService {

  String authenticateUser(LoginRequest loginRequest);

  Long registerUser(SignUpRequest request);

  Long registerCourier(SignUpRequest request);
}
