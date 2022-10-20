package com.sandsbeach.surfreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurfLocationReport {
    private String quality;
    private String waveAsHumanHeight;
    private String windDirection;
    private Double windSpeed;
    private Double windGust;


}
