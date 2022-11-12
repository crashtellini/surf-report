package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.surfline.SurflineAdapter;
import com.sandsbeach.surfreport.adapter.surfline.dto.SurflineTimestampData;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.SurflineTidesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.SurflineTideListDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.TideType;
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
        SurflineTidesDto surflineTides = getlastTides(locationId);
        SurfLocationReport response = new SurfLocationReport();

        //Rating
        String quality;

        //Wave
        String waveAsHumanHeight;

        //Wind
        String windDirection;
        Double windSpeed;
        Double windGust;

        //Tide

        TideType tideType;
        Double tideHeight;
        Long tideTime;


        // Map values and compute
        quality = convertSurflineQualityToSurfReportQuality(surflineRating.getRating().getValue());
        waveAsHumanHeight = surflineWave.getSurf().getHumanRelation();
        windDirection = surflineWind.getDirectionType();
        windSpeed = surflineWind.getSpeed();
        windGust = surflineWind.getGust();
        tideType = surflineTides.getType();
        tideHeight = surflineTides.getHeight();
        tideTime = surflineTides.getTimestamp();


        // Prepare output
        response.setQuality(quality);
        response.setWaveAsHumanHeight(waveAsHumanHeight);
        response.setWindDirection(windDirection);
        response.setWindSpeed(windSpeed);
        response.setWindGust(windGust);
        response.setTideType(tideType);
        response.setTideHeight(tideHeight);
        response.setTideTime(tideTime);

        return response;
    }

    private SurflineTidesDto getlastTides(String locationId) {
        SurflineTideListDto surflineTideListDto = surflineAdapter.getTides().getData();
        return surflineAdapter.getTides().getData().getTides().stream()
                .min(SurflineTimestampData.NEAREST)
                .orElse(new SurflineTidesDto());

    }

    private SurflineWindDto getlastWind(String locationId) {
      return surflineAdapter.getWinds().getData().getWind().stream()
              .min(SurflineTimestampData.NEAREST)
              .orElse(new SurflineWindDto());

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
                return "Drink a beer instead";
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
