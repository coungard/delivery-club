package com.coungard.controller;

import static com.coungard.config.SwaggerConfig.COURIER_SERVICE;

import com.coungard.model.CourierModel;
import com.coungard.model.request.RegisterCourierRequest;
import com.coungard.service.CourierService;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = COURIER_SERVICE)
@RestController
@RequiredArgsConstructor
@RequestMapping("/courier")
public class CourierController {

  private final CourierService courierService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public List<CourierModel> getAllCouriers() {
    return courierService.getAllCouriers();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/register")
  public CourierModel registerCourier(@RequestHeader("Authorization") String jwtToken,
      @RequestBody @Valid RegisterCourierRequest request) {
    System.out.println("jwtToken = " + jwtToken);
    return courierService.registerCourier(request, jwtToken);
  }
}
