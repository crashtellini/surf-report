package com.sandsbeach.surfreport.adapter.surfline.dto.wave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineWaveSurfDto {
    private String humanRelation;
}
