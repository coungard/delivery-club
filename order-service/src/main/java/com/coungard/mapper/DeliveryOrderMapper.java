package com.coungard.mapper;

import com.coungard.entity.DeliveryOrder;
import com.coungard.model.DeliveryOrderModel;
import com.coungard.model.request.CreateDeliveryOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryOrderMapper {

  DeliveryOrderMapper INSTANCE = Mappers.getMapper(DeliveryOrderMapper.class);

  DeliveryOrder toDeliveryOrder(CreateDeliveryOrderRequest request);

  DeliveryOrderModel toDeliveryOrderModel(DeliveryOrder deliveryOrder);
}
