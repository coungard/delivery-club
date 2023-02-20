package com.coungard.service.impl;

import com.coungard.entity.User;
import com.coungard.mapper.UserMapper;
import com.coungard.repository.UserRepository;
import com.coungard.security.UserPrincipal;
import com.coungard.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper = UserMapper.INSTANCE;

  @Override
  public List<UserPrincipal> getAllUsers() {
    List<User> users = userRepository.findAll();
    return userMapper.toPrincipalList(users);
  }

  @Override
  public UserPrincipal getUserByEmail(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));
    return userMapper.toPrincipal(user);
  }
}
