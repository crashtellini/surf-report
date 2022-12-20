package com.sandsbeach.surfreport.adapter.surflies.dto.wind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesTimestampData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesWindDto extends SurfliesTimestampData {

    private Double speed;
    private String directionType;
    private Double gust;
    private Double direction;
}
