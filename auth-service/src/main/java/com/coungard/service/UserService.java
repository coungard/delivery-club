package com.coungard.service;

import com.coungard.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

public interface UserService {

  List<User> getAllUsers();
}
