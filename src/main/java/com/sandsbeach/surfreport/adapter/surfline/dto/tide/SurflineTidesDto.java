package com.sandsbeach.surfreport.adapter.surfline.dto.tide;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surfline.dto.SurflineTimestampData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineTidesDto extends SurflineTimestampData {
    private TideType type;
    private Double height;
}
