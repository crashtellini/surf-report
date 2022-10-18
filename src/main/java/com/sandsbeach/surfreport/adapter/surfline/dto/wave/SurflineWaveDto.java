package com.sandsbeach.surfreport.adapter.surfline.dto.wave;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineWaveDto {
    private SurflineWaveSurfDto surf;
    // TODO: Add swells object
}
