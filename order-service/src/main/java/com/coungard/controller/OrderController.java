package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.ORDER_TAG;

import com.coungard.entity.DeliveryOrder;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.model.request.GetOrderRequest;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = ORDER_TAG)
@RestController
@RequestMapping("/order")
public class OrderController {

  @PreAuthorize("hasRole('ADMIN')")
  public String getOrder() {
    return "ALLOWED";
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('USER')")
  public String createOrder(@Valid @RequestBody CreateDeliveryOrderRequest request) {
    return "created";
  }
}
