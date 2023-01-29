package com.coungard.service.impl;

import com.coungard.entity.Role;
import com.coungard.entity.User;
import com.coungard.exception.ApplicationError;
import com.coungard.exception.ConflictException;
import com.coungard.model.RoleName;
import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.repository.RoleRepository;
import com.coungard.repository.UserRepository;
import com.coungard.service.AuthService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public String authenticateUser(LoginRequest loginRequest) {
    // todo
    return null;
  }

  @Override
  public Long registerUser(SignUpRequest request) {
    return register(request, RoleName.USER);
  }

  @Override
  public Long registerCourier(SignUpRequest request) {
    return register(request, RoleName.COURIER);
  }

  private Long register(SignUpRequest request, RoleName roleName) {
    String email = request.getEmail();
    if (userRepository.existsUserByEmail(email)) {
      throw new ConflictException("Email " + email + " is already taken!");
    }
    String password = passwordEncoder.encode(request.getPassword());
    Role role = roleRepository.findByName()
        .orElseThrow(() -> new ApplicationError("Role " + roleName + " is not in the list of roles"));
    User user = new User(email, request.getName(), password);
    user.setRoles(Collections.singleton(role));
    return userRepository.save(user).getId();
  }
}
