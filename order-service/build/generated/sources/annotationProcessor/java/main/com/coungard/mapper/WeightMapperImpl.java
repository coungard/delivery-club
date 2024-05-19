package com.coungard.mapper;

import com.coungard.entity.Weight;
import com.coungard.model.WeightModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class WeightMapperImpl implements WeightMapper {

    @Override
    public WeightModel toWeightDto(Weight weight) {
        if ( weight == null ) {
            return null;
        }

        WeightModel weightModel = new WeightModel();

        weightModel.setType( weight.getParcelType() );
        if ( weight.getMinWeight() != null ) {
            weightModel.setMinWeight( weight.getMinWeight().doubleValue() );
        }
        if ( weight.getMaxWeight() != null ) {
            weightModel.setMaxWeight( weight.getMaxWeight().doubleValue() );
        }

        return weightModel;
    }
}
