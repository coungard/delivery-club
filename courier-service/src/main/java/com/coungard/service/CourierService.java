package com.coungard.service;

import com.coungard.model.CourierModel;
import com.coungard.model.request.RegisterCourierRequest;
import java.util.List;

public interface CourierService {

  List<CourierModel> getAllCouriers();

  CourierModel registerCourier(RegisterCourierRequest request, String jwtToken);
}
