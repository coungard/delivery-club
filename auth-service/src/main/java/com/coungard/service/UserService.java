package com.coungard.service;

import com.coungard.entity.User;
import com.coungard.security.UserPrincipal;
import java.util.List;

public interface UserService {

  List<UserPrincipal> getAllUsers();

  UserPrincipal getUserByEmail(String email);
}
