package com.coungard.service.impl;

import com.coungard.entity.Role;
import com.coungard.entity.User;
import com.coungard.exception.ApplicationException;
import com.coungard.exception.ConflictException;
import com.coungard.mapper.UserMapper;
import com.coungard.model.RoleName;
import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;
import com.coungard.model.response.DetailedAuthenticationResponse;
import com.coungard.repository.RoleRepository;
import com.coungard.repository.UserRepository;
import com.coungard.security.CustomUserDetailsService;
import com.coungard.security.JwtService;
import com.coungard.security.UserPrincipal;
import com.coungard.service.AuthService;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
  private final CustomUserDetailsService userDetailsService;
  private final JwtService jwtService;
  private final UserMapper userMapper = UserMapper.INSTANCE;

  @Override
  public DetailedAuthenticationResponse authenticateUser(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    UserPrincipal userPrincipal = userDetailsService.loadUserByUsername(request.getEmail());
    String jwtToken = jwtService.generateToken(userPrincipal);

    log.info("User with [email: {}] has logged in", request.getEmail());

    return DetailedAuthenticationResponse.builder()
        .token(jwtToken)
        .userPrincipal(userPrincipal)
        .build();
  }

  @Override
  public AuthenticationResponse registerUser(SignUpRequest request) {
    return register(request, RoleName.ROLE_USER);
  }

  @Override
  public AuthenticationResponse registerCourier(SignUpRequest request) {
    return register(request, RoleName.ROLE_COURIER);
  }

  @Override
  public UserPrincipal identify(String authHeader) {
    return Optional.of(authHeader)
        .map(this::defineBearerToken)
        .map(jwtService::extractUsername)
        .map(userRepository::findByEmail)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(userMapper::toPrincipal)
        .orElseThrow(() -> new RuntimeException("Identification error by extract token: " + authHeader));
  }

  private String defineBearerToken(String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new RuntimeException("Authentication method must be Bearer");
    }
    return authHeader.substring(7);
  }

  private AuthenticationResponse register(SignUpRequest request, RoleName roleName) {
    String email = request.getEmail();
    if (userRepository.existsUserByEmail(email)) {
      throw new ConflictException("Email " + email + " is already taken!");
    }
    String password = passwordEncoder.encode(request.getPassword());
    Role role = roleRepository.findByName(roleName.name())
        .orElseThrow(() -> new ApplicationException("Role " + roleName + " is not in the list of roles"));
    User user = new User(email, request.getName(), password);
    user.setRoles(Collections.singleton(role));
    userRepository.save(user);

    String jwtToken = jwtService.generateToken(userMapper.toPrincipal(user));
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .roleName(roleName.name())
        .build();
  }
}
