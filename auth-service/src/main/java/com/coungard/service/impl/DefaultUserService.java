package com.coungard.service.impl;

import com.coungard.entity.User;
import com.coungard.model.RoleName;
import com.coungard.repository.UserRepository;
import com.coungard.request.SignUpRequest;
import com.coungard.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void createUser(SignUpRequest request, RoleName user) {
    // todo 1 - проверка что не существует юзера с таким email
    // todo 2  - сохранение в базу
  }
}
