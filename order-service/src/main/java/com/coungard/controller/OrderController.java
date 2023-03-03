package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.ORDER_TAG;

import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.service.OrderService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = ORDER_TAG)
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('USER')")
  public DeliveryOrderModel createOrder(@Valid @RequestBody CreateDeliveryOrderRequest request) {
    return orderService.createOrder(request);
  }
}
