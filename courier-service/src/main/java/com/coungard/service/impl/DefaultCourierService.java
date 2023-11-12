package com.coungard.service.impl;

import com.coungard.client.AuthClient;
import com.coungard.entity.Courier;
import com.coungard.mapper.CourierMapper;
import com.coungard.model.CourierModel;
import com.coungard.model.request.RegisterCourierRequest;
import com.coungard.model.request.SignUpRequest;
import com.coungard.model.response.AuthenticationResponse;
import com.coungard.repository.CourierRepository;
import com.coungard.service.CourierService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCourierService implements CourierService {

  private final CourierRepository courierRepository;
  private final AuthClient authClient;
  private final CourierMapper courierMapper = CourierMapper.INSTANCE;

  @Override
  public List<CourierModel> getAllCouriers() {
    List<Courier> courierList = courierRepository.findAll();
    return courierMapper.toCourierModelList(courierList);
  }

  @Override
  public CourierModel registerCourier(RegisterCourierRequest request, String jwtToken) {
    AuthenticationResponse response = authClient.registerCourier(SignUpRequest.builder()
        .email(request.getEmail())
        .name(request.getName())
        .password(request.getPassword())
        .build(), jwtToken);
    if (response == null) {
      throw new RuntimeException("No response after try register courier");
    }
    Courier courier = Courier.builder()
        .userId(response.getId())
        .type(request.getType())
        .status(request.getStatus())
//        .createdDate(LocalDateTime.now())
//        .createdBy(definePrincipal())
        .build();
    courierRepository.save(courier);

    return courierMapper.toCourierModel(courier);
  }

  private String definePrincipal() {
    return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
