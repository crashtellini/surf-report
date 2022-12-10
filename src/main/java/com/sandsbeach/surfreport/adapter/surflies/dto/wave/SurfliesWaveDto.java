package com.sandsbeach.surfreport.adapter.surflies.dto.wave;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesWaveDto {
    private SurfliesWaveSurfDto surf;
    // TODO: Add swells object
}
