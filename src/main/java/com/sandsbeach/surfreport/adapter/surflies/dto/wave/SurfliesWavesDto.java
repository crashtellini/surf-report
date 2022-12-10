package com.sandsbeach.surfreport.adapter.surflies.dto.wave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesWavesDto {
    private List<SurfliesWaveDto> wave;
}
