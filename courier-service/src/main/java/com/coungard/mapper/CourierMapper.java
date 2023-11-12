package com.coungard.mapper;

import com.coungard.entity.Courier;
import com.coungard.model.CourierModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourierMapper {

  CourierMapper INSTANCE = Mappers.getMapper(CourierMapper.class);

  List<CourierModel> toCourierModelList(List<Courier> courierList);

  CourierModel toCourierModel(Courier courier);
}
