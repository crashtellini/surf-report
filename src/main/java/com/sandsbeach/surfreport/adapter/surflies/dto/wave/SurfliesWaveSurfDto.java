package com.sandsbeach.surfreport.adapter.surflies.dto.wave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesWaveSurfDto {
    private String humanRelation;
}
