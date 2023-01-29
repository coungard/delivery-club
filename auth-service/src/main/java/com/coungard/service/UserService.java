package com.coungard.service;

import com.coungard.entity.User;
import com.coungard.model.RoleName;
import com.coungard.request.SignUpRequest;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  void createUser(SignUpRequest request, RoleName user);
}
