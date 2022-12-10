package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.surflies.SurfliesAdapter;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesTimestampData;
import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingsDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.tide.SurfliesTidesDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.tide.TideType;
import com.sandsbeach.surfreport.adapter.surflies.dto.wave.SurfliesWaveDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wave.SurfliesWavesDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wind.SurfliesWindDto;
import com.sandsbeach.surfreport.model.SurfLocationReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class SurfliesService {

    private static final String SPOT_ID = "5842041f4e65fad6a7708964";
    private static final int DAYS = 16;
    private static final int INTERVAL_HOURS = 1;
    private static final boolean CORRECTED = true;

    @Autowired
    private SurfliesAdapter surfliesAdapter;


    public SurfLocationReport getReport(String locationId) {
        // Prepare variables

        SurfliesRatingDto surfliesRating = getLastRating(locationId);
        SurfliesWaveDto surfliesWave = getLastWave(locationId);
        SurfliesWindDto surfliesWind = getlastWind(locationId);
        SurfliesTidesDto surfliesTides = getlastTides(locationId);
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
        quality = convertSurfliesQualityToSurfReportQuality(surfliesRating.getRating().getValue());
        waveAsHumanHeight = surfliesWave.getSurf().getHumanRelation();
        windDirection = surfliesWind.getDirectionType();
        direction = surfliesWind.getDirection();
        windSpeed = surfliesWind.getSpeed();
        windGust = surfliesWind.getGust();
        tideType = surfliesTides.getType();
        tideHeight = surfliesTides.getHeight();
        tideTime = surfliesTides.getTimestamp();


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

    private SurfliesTidesDto getlastTides(String locationId) {
        return surfliesAdapter.getTides(
                        SPOT_ID,
                        DAYS,
                        INTERVAL_HOURS,
                        CORRECTED
                ).getData().getTides().stream()
                .min(SurfliesTimestampData.NEAREST)
                .orElse(new SurfliesTidesDto());
    }

    private SurfliesWindDto getlastWind(String locationId) {
        return surfliesAdapter.getWinds(
                        SPOT_ID,
                        DAYS,
                        INTERVAL_HOURS,
                        CORRECTED
                ).getData().getWind().stream()
                .min(SurfliesTimestampData.NEAREST)
                .orElse(new SurfliesWindDto());
    }

    private SurfliesWaveDto getLastWave(String locationId) {
        SurfliesWavesDto surfliesWavesDto = surfliesAdapter.getWaves(
                SPOT_ID,
                DAYS,
                INTERVAL_HOURS
        ).getData();
        return surfliesWavesDto.getWave().get(surfliesWavesDto.getWave().size() - 1);
    }


    private SurfliesRatingDto getLastRating(String locationId) {
        // TODO convert locationId to spotId, use a config class
        SurfliesRatingsDto surfliesRatingsDto = surfliesAdapter.getRating(
                SPOT_ID,
                DAYS,
                INTERVAL_HOURS
        ).getData();
        return surfliesRatingsDto.getRating().get(surfliesRatingsDto.getRating().size() - 1);
    }




    private String convertSurfliesQualityToSurfReportQuality(String value) {
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
                return "Woah! Surflies sucks";
        }

    }

//    @Cacheable(cacheNames = Surflies_TOKEN_CACHE)
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