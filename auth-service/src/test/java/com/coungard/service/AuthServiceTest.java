package com.coungard.service;

import static com.coungard.model.RoleName.ROLE_COURIER;
import static com.coungard.model.RoleName.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.coungard.entity.Role;
import com.coungard.entity.User;
import com.coungard.exception.ApplicationException;
import com.coungard.exception.ConflictException;
import com.coungard.model.request.LoginRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;
import com.coungard.model.response.DetailedAuthenticationResponse;
import com.coungard.repository.RoleRepository;
import com.coungard.repository.UserRepository;
import com.coungard.security.JwtService;
import com.coungard.security.UserPrincipal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceTest {

  private static final String USER_EXISTS_EMAIL = "test@gmail.com";
  private static final String SIGN_UP_EMAIL = "signup@gmail.com";
  private static final String REGISTER_COURIER_EMAIL = "courier@gmail.com";
  private static final String NOT_EXISTS_EMAIL = "non_exists@gmail.com";
  private static final String USER_NAME = "Bobby";
  private static final String USER_PASSWORD = "12345678";
  private static final String INCORRECT_PASSWORD = "incorrect_pass";
  private static final String ENCRYPTED_PASSWORD = "$2a$10$Uk6ESOOrW0FZQ1llPN71XOGyc0izXEeG7uJ2xTBhFpgDubpaXRk0K";

  @Autowired
  private AuthService authService;
  @Autowired
  private JwtService jwtService;
  @MockBean
  private UserRepository userRepository;
  @MockBean
  private RoleRepository roleRepository;
  @Mock
  private UserDetails userDetails;

  @BeforeEach
  public void setup() {
    Mockito.when(userRepository.findByEmail(USER_EXISTS_EMAIL)).thenReturn(defineTestUser());
    Mockito.when(userRepository.findByEmail(NOT_EXISTS_EMAIL)).thenReturn(Optional.empty());
    Mockito.when(userRepository.existsUserByEmail(USER_EXISTS_EMAIL)).thenReturn(true);

    Optional<Role> userRole = Optional.of(Role.builder().name(ROLE_USER).build());
    Optional<Role> courierRole = Optional.of(Role.builder().name(ROLE_COURIER).build());
    Mockito.when(roleRepository.findByName(ROLE_USER.name())).thenReturn(userRole);
    Mockito.when(roleRepository.findByName(ROLE_COURIER.name())).thenReturn(courierRole);

    Mockito.when(userDetails.getUsername()).thenReturn(USER_EXISTS_EMAIL);
  }

  @Test
  @DisplayName("Authenticate - success")
  public void successAuthenticateUser() {
    LoginRequest loginRequest = LoginRequest.builder()
        .email(USER_EXISTS_EMAIL)
        .password(USER_PASSWORD)
        .build();

    DetailedAuthenticationResponse response = authService.authenticateUser(loginRequest);
    assertTrue(jwtService.isTokenValid(response.getToken(), response.getUserPrincipal()));

    UserPrincipal user = response.getUserPrincipal();
    assertEquals(USER_EXISTS_EMAIL, user.getUsername());
    assertEquals(USER_NAME, user.getName());
    assertEquals(ENCRYPTED_PASSWORD, user.getPassword());
    var authorities = user.getAuthorities();
    assertEquals(1, authorities.size());
    var role = authorities.stream().findFirst();
    assertTrue(role.isPresent());
    assertEquals(ROLE_USER.name(), role.get().getAuthority());
  }

  @Test
  @DisplayName("Authenticate - bad credentials")
  public void badCredentialsAuthenticateUser() {
    LoginRequest requestWithIncorrectPass = LoginRequest.builder()
        .email(USER_EXISTS_EMAIL)
        .password(INCORRECT_PASSWORD)
        .build();

    LoginRequest requestWithNonExistsEmail = LoginRequest.builder()
        .email(NOT_EXISTS_EMAIL)
        .password(USER_PASSWORD)
        .build();

    BadCredentialsException incorrectPassException = assertThrows(BadCredentialsException.class,
        () -> authService.authenticateUser(requestWithIncorrectPass));
    BadCredentialsException nonExistsEmailException = assertThrows(BadCredentialsException.class,
        () -> authService.authenticateUser(requestWithNonExistsEmail));
    assertEquals("Bad credentials", incorrectPassException.getMessage());
    assertEquals("Bad credentials", nonExistsEmailException.getMessage());
  }

  @Test
  @DisplayName("Sign Up - success")
  public void successfulSignUp() {
    SignUpRequest signUpRequest = SignUpRequest.builder()
        .email(SIGN_UP_EMAIL)
        .name(USER_NAME)
        .password(USER_PASSWORD)
        .build();

    AuthenticationResponse response = authService.registerUser(signUpRequest);
    String username = jwtService.extractUsername(response.getToken());
    assertEquals(ROLE_USER.name(), response.getRoleName());
    assertEquals(SIGN_UP_EMAIL, username);
  }

  @Test
  @DisplayName("Register courier - success")
  public void successfulRegisterCourier() {
    SignUpRequest signUpRequest = SignUpRequest.builder()
        .email(REGISTER_COURIER_EMAIL)
        .name(USER_NAME)
        .password(USER_PASSWORD)
        .build();

    AuthenticationResponse response = authService.registerCourier(signUpRequest);
    String username = jwtService.extractUsername(response.getToken());
    assertEquals(ROLE_COURIER.name(), response.getRoleName());
    assertEquals(REGISTER_COURIER_EMAIL, username);
  }

  @Test
  @DisplayName("Sign Up - email already taken")
  public void signUpFailedEmailAlreadyTaken() {
    SignUpRequest signUpRequest = SignUpRequest.builder()
        .email(USER_EXISTS_EMAIL)
        .name(USER_NAME)
        .password(USER_PASSWORD)
        .build();

    ConflictException conflictException = assertThrows(ConflictException.class,
        () -> authService.registerUser(signUpRequest));
    assertEquals("Email " + USER_EXISTS_EMAIL + " is already taken!", conflictException.getMessage());
  }

  @Test
  @DisplayName("Identify - success")
  public void successIdentify() {
    String token = jwtService.generateToken(userDetails);

    UserPrincipal principal = authService.identify("Bearer " + token);
    assertEquals(USER_NAME, principal.getName());
    assertEquals(USER_EXISTS_EMAIL, principal.getUsername());
    assertEquals(ENCRYPTED_PASSWORD, principal.getPassword());

    var authorities = principal.getAuthorities();
    var role = authorities.stream().findFirst();
    assertTrue(role.isPresent());
    assertEquals(ROLE_USER.name(), role.get().getAuthority());
  }

  @Test
  @DisplayName("Identify - incorrect header")
  public void identifyWithIncorrectHeader() {
    String token = jwtService.generateToken(userDetails);

    ApplicationException applicationException = assertThrows(ApplicationException.class,
        () -> authService.identify("No Bearer " + token));
    assertEquals("Authentication method must be Bearer", applicationException.getMessage());
  }

  private static Optional<User> defineTestUser() {
    return Optional.of(new User()
        .withId(1L)
        .withEmail(USER_EXISTS_EMAIL)
        .withName(USER_NAME)
        .withPassword(ENCRYPTED_PASSWORD)
        .withRoles(defineRoles()));
  }

  private static Set<Role> defineRoles() {
    return Collections.singleton(Role.builder()
        .id(1)
        .name(ROLE_USER)
        .build());
  }
}
