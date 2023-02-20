package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.ORDER_TAG;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = ORDER_TAG)
@RestController
@RequestMapping("/order")
public class OrderController {

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public String createOrder() {
    return "created";
  }
}
