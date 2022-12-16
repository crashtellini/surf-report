package com.sandsbeach.surfreport.model;

import com.sandsbeach.surfreport.adapter.surflies.dto.tide.TideType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SurfLocationReport  {



    //rating
    private String quality;

    //wave
    private String waveAsHumanHeight;

    //wind
    private String windDirection;
    private Double windSpeed;
    private Double windGust;
    private Double direction;

    //tide
    private String tideType;
    private Double tideHeight;
    private Long tideTime;

    //buoys

    private Double buoyHeight;
    private Double buoyPeriod;
    private Double buoyDirection;








}
