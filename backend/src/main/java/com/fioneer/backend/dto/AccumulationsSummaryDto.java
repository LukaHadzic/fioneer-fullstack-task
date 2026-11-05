package com.fioneer.backend.dto;

import java.util.List;

public class AccumulationsSummaryDto {

    private List<AverageSumInsuredPerStatusDto> averageSumInsuredPerStatusDto;
    private List<AccumulationsCreatedPerDateDto> accumulationsCreatedPerDateDto;

    public AccumulationsSummaryDto(List<AverageSumInsuredPerStatusDto> averageSumInsuredPerStatusDto, List<AccumulationsCreatedPerDateDto> accumulationsCreatedPerDateDto) {
        this.averageSumInsuredPerStatusDto = averageSumInsuredPerStatusDto;
        this.accumulationsCreatedPerDateDto = accumulationsCreatedPerDateDto;
    }

    public List<AverageSumInsuredPerStatusDto> getAverageSumInsuredPerStatusDto() {
        return averageSumInsuredPerStatusDto;
    }

    public void setAverageSumInsuredPerStatusDto(List<AverageSumInsuredPerStatusDto> averageSumInsuredPerStatusDto) {
        this.averageSumInsuredPerStatusDto = averageSumInsuredPerStatusDto;
    }

    public List<AccumulationsCreatedPerDateDto> getAccumulationsCreatedPerDateDto() {
        return accumulationsCreatedPerDateDto;
    }

    public void setAccumulationsCreatedPerDateDto(List<AccumulationsCreatedPerDateDto> accumulationsCreatedPerDateDto) {
        this.accumulationsCreatedPerDateDto = accumulationsCreatedPerDateDto;
    }
}
