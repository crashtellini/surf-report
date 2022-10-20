package com.sandsbeach.surfreport.adapter.surfline.dto.wind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineWindDto {

    private double speed;
    private String directionType;
    private double gust;
}
