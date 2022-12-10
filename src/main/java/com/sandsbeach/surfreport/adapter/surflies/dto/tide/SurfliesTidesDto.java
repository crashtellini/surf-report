package com.sandsbeach.surfreport.adapter.surflies.dto.tide;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesTimestampData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesTidesDto extends SurfliesTimestampData {
    private TideType type;
    private Double height;
}
