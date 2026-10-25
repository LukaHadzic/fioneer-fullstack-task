package com.fioneer.backend.mapper;

import com.fioneer.backend.dto.AccumulationDto;
import com.fioneer.backend.entity.Accumulation;
import org.springframework.stereotype.Component;

@Component
public class AccumulationMapper {

    public AccumulationMapper() {
    }

    public AccumulationDto toAccumulationDto(Accumulation accumulation){
        return new AccumulationDto(accumulation.getName(), accumulation.getClassOfBusiness().getName(), accumulation.getStatus().getStatus(), accumulation.getDescription(), accumulation.getCoverages());
    }
}
