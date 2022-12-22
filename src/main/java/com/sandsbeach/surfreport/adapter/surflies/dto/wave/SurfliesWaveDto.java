package com.sandsbeach.surfreport.adapter.surflies.dto.wave;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesTimestampData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesWaveDto extends SurfliesTimestampData {
    private SurfliesWaveSurfDto surf;
    // TODO: Add swells object
}
