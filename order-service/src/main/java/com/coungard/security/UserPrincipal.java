package com.coungard.security;

import com.coungard.utils.UserPrincipalAuthoritiesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPrincipal implements UserDetails {

  private Long id;
  private String name;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  @Override
  public String getUsername() {
    return email;
  }

  public String getName() {
    return name;
  }

  @Override
  @JsonDeserialize(using = UserPrincipalAuthoritiesDeserializer.class)
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
