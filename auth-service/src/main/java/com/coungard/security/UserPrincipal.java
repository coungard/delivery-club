package com.coungard.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Collection;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@JsonPropertyOrder(value = {
    "name",
    "email",
    "getAuthority"
})
public class UserPrincipal implements UserDetails {

  private final Long id;
  private final String name;
  private final String email;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;

  @JsonCreator
  public String getAuthority() {
    return authorities.stream()
        .map(GrantedAuthority::getAuthority)
        .findFirst()
        .orElseThrow();
  }

  @Override
  @JsonProperty("email")
  public String getUsername() {
    return email;
  }

  public String getName() {
    return name;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
