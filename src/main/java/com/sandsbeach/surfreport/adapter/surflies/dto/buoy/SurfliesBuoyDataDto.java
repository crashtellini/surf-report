package com.sandsbeach.surfreport.adapter.surflies.dto.buoy;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesBuoyDataDto {
    private Double height;
    private Double period;
    private Double direction;
}
