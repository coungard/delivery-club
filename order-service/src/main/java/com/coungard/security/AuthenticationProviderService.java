package com.coungard.security;

import com.coungard.client.AuthClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

  private final AuthClient authClient;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getPrincipal().toString();
    String password = authentication.getCredentials().toString();

    UserPrincipal userPrincipal;
    try {
      userPrincipal = authClient.getUserByEmail(username);
    } catch (FeignException.Unauthorized ex) {
      throw new BadCredentialsException("Bad credentials");
    } catch (Exception exception) {
      throw new RuntimeException(exception.getMessage());
    }

    return checkPassword(userPrincipal, password); // admin@gmail.com  1234 = 12345678
  }

  private Authentication checkPassword(UserPrincipal user, String password) {
    if (passwordEncoder.matches(password, user.getPassword())) {
      return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    } else {
      throw new BadCredentialsException("Bad credentials");
    }
  }

  @Override
  public boolean supports(Class<?> authenticationType) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
  }
}