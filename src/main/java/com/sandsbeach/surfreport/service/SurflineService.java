package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.surfline.SurflineAdapter;
import com.sandsbeach.surfreport.adapter.surfline.dto.SurflineTimestampData;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.SurflineTidesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.TideType;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWaveDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWavesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindDto;
import com.sandsbeach.surfreport.model.SurfLocationReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class SurflineService {

    private static final String SPOT_ID = "5842041f4e65fad6a7708964";
    private static final int DAYS = 16;
    private static final int INTERVAL_HOURS = 1;
    private static final boolean CORRECTED = true;

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
        Double direction;

        //Tide

        TideType tideType;
        Double tideHeight;
        Long tideTime;


        // Map values and compute
        quality = convertSurflineQualityToSurfReportQuality(surflineRating.getRating().getValue());
        waveAsHumanHeight = surflineWave.getSurf().getHumanRelation();
        windDirection = surflineWind.getDirectionType();
        direction = surflineWind.getDirection();
        windSpeed = surflineWind.getSpeed();
        windGust = surflineWind.getGust();
        tideType = surflineTides.getType();
        tideHeight = surflineTides.getHeight();
        tideTime = surflineTides.getTimestamp();


        // Prepare output
        response.setQuality(quality);
        response.setWaveAsHumanHeight(waveAsHumanHeight);
        response.setWindDirection(windDirection);
        response.setDirection(direction);
        response.setWindSpeed(windSpeed);
        response.setWindGust(windGust);
        response.setTideType(tideType);
        response.setTideHeight(tideHeight);
        response.setTideTime(tideTime);

        return response;
    }

    private SurflineTidesDto getlastTides(String locationId) {
        return surflineAdapter.getTides(
                        SPOT_ID,
                        DAYS,
                        INTERVAL_HOURS,
                        CORRECTED
                ).getData().getTides().stream()
                .min(SurflineTimestampData.NEAREST)
                .orElse(new SurflineTidesDto());
    }

    private SurflineWindDto getlastWind(String locationId) {
        return surflineAdapter.getWinds(
                        SPOT_ID,
                        DAYS,
                        INTERVAL_HOURS,
                        CORRECTED
                ).getData().getWind().stream()
                .min(SurflineTimestampData.NEAREST)
                .orElse(new SurflineWindDto());
    }

    private SurflineWaveDto getLastWave(String locationId) {
        SurflineWavesDto surflineWavesDto = surflineAdapter.getWaves(
                SPOT_ID,
                DAYS,
                INTERVAL_HOURS
        ).getData();
        return surflineWavesDto.getWave().get(surflineWavesDto.getWave().size() - 1);
    }


    private SurflineRatingDto getLastRating(String locationId) {
        // TODO convert locationId to spotId, use a config class
        SurflineRatingsDto surflineRatingsDto = surflineAdapter.getRating(
                SPOT_ID,
                DAYS,
                INTERVAL_HOURS
        ).getData();
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

//    @Cacheable(cacheNames = SURFLINE_TOKEN_CACHE)
//    private String getAccessToken() {
//
//
//
//
//
//        // TODO call feign endpoint with login info to pull a new token
//        return "76a01cc852053203a9bea2d20404c4cd2b2b6d9e";
//    }
}