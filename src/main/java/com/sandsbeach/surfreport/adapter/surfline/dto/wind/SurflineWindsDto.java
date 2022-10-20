package com.sandsbeach.surfreport.adapter.surfline.dto.wind;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineWindsDto {

    private List<SurflineWindDto> wind;
}
