package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.ORDER_TAG;

import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.DeliveryOrderStatus;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @ApiOperation(value = "Create order by User")
  public DeliveryOrderModel createOrder(@Valid @RequestBody CreateDeliveryOrderRequest request) {
    return orderService.createOrder(request);
  }

  @GetMapping
  @PreAuthorize("hasRole('USER')")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get own User's orders")
  public List<DeliveryOrderModel> getOwnOrders(@RequestParam DeliveryOrderStatus status) {
    return orderService.getOwnOrders(status);
  }

  // jwt auth
  // delete own order
  // edit own order
  // get all own orders (by filter)
  // reset password
}
