package com.sandsbeach.surfreport.adapter.surflies.dto.buoy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingDto;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesBuoyDataListDto {
    private SurfliesBuoyDataDto latestData;
}






