package com.sandsbeach.surfreport.adapter.surfline.dto.tide;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class SurflineTidesDto {
    private String type;
    private Double height;
    private Long timestamp;
}
