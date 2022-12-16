package com.sandsbeach.surfreport.adapter.surflies.dto.buoy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesBuoyResponseDto {
    private List<SurfliesBuoyDataListDto> data;
}






