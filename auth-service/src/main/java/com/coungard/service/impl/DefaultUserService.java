package com.coungard.service.impl;

import com.coungard.entity.User;
import com.coungard.mapper.UserMapper;
import com.coungard.repository.UserRepository;
import com.coungard.security.UserPrincipal;
import com.coungard.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper = UserMapper.INSTANCE;

  @Override
  @Transactional
  public List<UserPrincipal> getAllUsers() {
    List<User> users = userRepository.findAll();
    return userMapper.toPrincipalList(users);
  }
}
