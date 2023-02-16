package com.coungard.controller;

import com.coungard.entity.DeliveryOrder;
import com.coungard.model.DeliveryOrderRequest;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public String createOrder(@RequestBody DeliveryOrderRequest deliveryOrderRequest) {
    return "created";
  }
}
