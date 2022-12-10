package com.sandsbeach.surfreport.adapter.surflies.dto.wind;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesWindsDto {

    private List<SurfliesWindDto> wind;
}
