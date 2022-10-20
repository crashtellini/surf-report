package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.surfline.SurflineAdapter;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWaveDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWavesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindsDto;
import com.sandsbeach.surfreport.model.SurfLocationReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SurflineService {

    @Autowired
    private SurflineAdapter surflineAdapter;


    public SurfLocationReport getReport(String locationId) {
        // Prepare variables

        SurflineRatingDto surflineRating = getLastRating(locationId);
        SurflineWaveDto surflineWave = getLastWave(locationId);
        SurflineWindDto surflineWind = getlastWind(locationId);
        SurfLocationReport response = new SurfLocationReport();

        //Rating
        String quality;

        //Wave
        String waveAsHumanHeight;

        //Wind
        String windDirection;
        Double windSpeed;
        Double windGust;


        // Map values and compute
        quality = convertSurflineQualityToSurfReportQuality(surflineRating.getRating().getValue());
        waveAsHumanHeight = surflineWave.getSurf().getHumanRelation();
        windDirection = surflineWind.getDirectionType();
        windSpeed = surflineWind.getSpeed();
        windGust = surflineWind.getGust();


        // Prepare output
        response.setQuality(quality);
        response.setWaveAsHumanHeight(waveAsHumanHeight);
        response.setWindDirection(windDirection);
        response.setWindSpeed(windSpeed);
        response.setWindGust(windGust);

        return response;
    }

    private SurflineWindDto getlastWind(String locationId) {
      SurflineWindsDto surflineWindsDto = surflineAdapter.getWinds().getData();
      return surflineWindsDto.getWind().get(surflineWindsDto.getWind().size()-1);

    }

    private SurflineWaveDto getLastWave(String locationId) {
        SurflineWavesDto surflineWavesDto = surflineAdapter.getWaves().getData();
        return surflineWavesDto.getWave().get(surflineWavesDto.getWave().size() - 1);
    }


    private SurflineRatingDto getLastRating(String locationId) {
        // TODO convert locationId to spotId, use a config class
        SurflineRatingsDto surflineRatingsDto = surflineAdapter.getRating().getData();
        return surflineRatingsDto.getRating().get(surflineRatingsDto.getRating().size() - 1);
    }


    private String convertSurflineQualityToSurfReportQuality(String value) {
        switch (value) {
            case "0":
                return "Don't even try";
            case "1":
                return "Hope you like paddling, that's all you'll be doing";
            case "2":
                return "Hmm..water is cold..suit is wet..better look at it for 40 minutes and then decide not to surf";
            case "3":
                return "Wet wetsuit? Nah not worth it";
            case "4":
                return "Possibly worth while ";
            case "5":
                return "Get out there now! Just like the other 1000 people in the line up";
            default:
                return "Woah! Surfline sucks";
        }
    }



}