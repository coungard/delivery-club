package com.coungard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spock.lang.Specification;

@SpringBootTest
public class AuthServiceTests extends Specification {

  @Autowired
  private AuthService authService; // todo Spock implementation on groovy
}
