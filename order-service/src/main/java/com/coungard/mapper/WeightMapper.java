package com.coungard.mapper;

import com.coungard.entity.Weight;
import com.coungard.model.WeightModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WeightMapper {

  WeightMapper INSTANCE = Mappers.getMapper(WeightMapper.class);

  @Mapping(source = "parcelType", target = "type")
  WeightModel toWeightDto(Weight weight);
}
