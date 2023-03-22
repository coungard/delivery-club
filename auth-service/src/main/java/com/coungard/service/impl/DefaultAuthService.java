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
import com.coungard.repository.RoleRepository;
import com.coungard.repository.UserRepository;
import com.coungard.security.CustomUserDetailsService;
import com.coungard.security.JwtService;
import com.coungard.service.AuthService;
import java.util.Collections;
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
  public AuthenticationResponse authenticateUser(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
    String jwtToken = jwtService.generateToken(userDetails);

    log.info("User with [email: {}] has logged in", request.getEmail());
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .roleName(userDetails.getAuthorities().stream().findFirst().get().toString())
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
