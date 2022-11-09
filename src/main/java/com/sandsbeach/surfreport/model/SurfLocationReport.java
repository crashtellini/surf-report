package com.sandsbeach.surfreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurfLocationReport {

    //rating
    private String quality;

    //wave
    private String waveAsHumanHeight;

    //wind
    private String windDirection;
    private Double windSpeed;
    private Double windGust;

    //tide
    private String tideType;
    private Double tideHeight;
    private Long tideTime;




}
