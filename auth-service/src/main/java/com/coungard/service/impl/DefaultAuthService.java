package com.coungard.service.impl;

import com.coungard.entity.Role;
import com.coungard.entity.User;
import com.coungard.exception.ApplicationException;
import com.coungard.exception.ConflictException;
import com.coungard.model.RoleName;
import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.repository.RoleRepository;
import com.coungard.repository.UserRepository;
import com.coungard.security.UserPrincipal;
import com.coungard.service.AuthService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthService implements AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  public boolean authenticateUser(LoginRequest request) {
    Authentication authentication;
    try {
      authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getEmail(),
              request.getPassword())
      );
    } catch (BadCredentialsException ex) {
      log.error(ex.getMessage());
      return false;
    }
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    log.info("User with [email: {}] has logged in", principal.getUsername());
    return true;
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
    Role role = roleRepository.findByName(roleName.name())
        .orElseThrow(() -> new ApplicationException("Role " + roleName + " is not in the list of roles"));
    User user = new User(email, request.getName(), password);
    user.setRoles(Collections.singleton(role));
    return userRepository.save(user).getId();
  }
}
