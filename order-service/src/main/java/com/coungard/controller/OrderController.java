package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.ORDER_TAG;

import com.coungard.model.AddressModel;
import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.WeightModel;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import com.coungard.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  @ApiOperation(value = "Create order by User")
  public DeliveryOrderModel createOrder(@Valid @RequestBody CreateDeliveryOrderRequest request) {
    return orderService.createOrder(request);
  }

  @GetMapping
  @PreAuthorize("hasRole('USER')")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get own User's orders")
  public List<DeliveryOrderModel> getOwnOrders() {
    return orderService.getOwnOrders();
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Change the destination address of a user's own order")
  public DeliveryOrderModel changeDestination(@Valid @RequestBody AddressModel destination, @PathVariable Long id) {
    return orderService.changeDestination(id, destination);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('USER')")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Delete delivery order by User")
  public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
    Long deleted = orderService.deleteOrder(id);
    return ResponseEntity.ok().body(deleted);
  }

  @GetMapping("/weight-map")
  public ResponseEntity<List<WeightModel>> getWeightsList() {
    List<WeightModel> weightMap = orderService.getWeightsList();

    if (weightMap.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(weightMap);
    }
  }
}
