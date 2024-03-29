package com.coungard.security;

import java.util.Collection;
import lombok.Builder;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@ToString
public class UserPrincipal implements UserDetails {

  private final Long id;
  private final String name;
  private final String email;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;

  @Override
  public String getUsername() {
    return email;
  }

  public String getName() {
    return name;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
