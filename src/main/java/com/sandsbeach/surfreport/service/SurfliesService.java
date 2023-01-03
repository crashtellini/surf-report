package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.surflies.SurfliesAdapter;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesTimestampData;
import com.sandsbeach.surfreport.adapter.surflies.dto.buoy.SurfliesBuoyDataDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.buoy.SurfliesBuoyDataListDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingsDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.tide.SurfliesTidesDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.tide.TideType;
import com.sandsbeach.surfreport.adapter.surflies.dto.wave.SurfliesWaveDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wave.SurfliesWavesDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wind.SurfliesWindDto;
import com.sandsbeach.surfreport.model.SurfLocationReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;

@Slf4j
@Service
public class SurfliesService {

    private static final String SPOT_ID = "5842041f4e65fad6a7708964";
    private static final int INTERVAL_HOURS = 1;
    private static final boolean CORRECTED = true;
    private static final String REFRESH_SCHEDULE = "0 */2 9-17 * * *";

    @Autowired
    private SurfliesAdapter surfliesAdapter;


    public SurfLocationReport getReport(String locationId) {
        // Prepare variables

        SurfliesRatingDto surfliesRating = getLastRating(locationId);
        SurfliesWaveDto surfliesWave = getLastWave(locationId);
        SurfliesWindDto surfliesWind = getLastWind(locationId);
        SurfliesTidesDto surfliesTides = getLastTides(locationId);;
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

        String tideType;
        Double tideHeight;
        Long tideTime;

        //Buoys

        Double buoyHeight;
        Double buoyPeriod;
        Double buoyDirection;




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


        return response;
    }



    private SurfliesTidesDto getLastTides(String locationId) {
        return surfliesAdapter.getTides(
                        SPOT_ID,
                        INTERVAL_HOURS,
                        CORRECTED
                ).getData().getTides().stream()
                .min(SurfliesTimestampData.NEAREST)
                .orElse(new SurfliesTidesDto());
    }

    private SurfliesWindDto getLastWind(String locationId) {
        return surfliesAdapter.getWinds(
                        SPOT_ID,
                        INTERVAL_HOURS,
                        CORRECTED
                ).getData().getWind().stream()
                .min(SurfliesTimestampData.NEAREST)
                .orElse(new SurfliesWindDto());
    }

    private SurfliesWaveDto getLastWave(String locationId) {
        return surfliesAdapter.getWaves(
                        SPOT_ID,
                        INTERVAL_HOURS
                ).getData().getWave().stream()
                .min(SurfliesTimestampData.NEAREST)
                .orElse(new SurfliesWaveDto());
    }

    private SurfliesRatingDto getLastRating(String locationId) {
        return surfliesAdapter.getRatings(
                        SPOT_ID,
                        INTERVAL_HOURS
                ).getData().getRating().stream()
                .min(SurfliesTimestampData.NEAREST)
                .orElse(new SurfliesRatingDto());
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

    @Scheduled(cron = REFRESH_SCHEDULE)
    public void primeAllEndpoints() {
        primeRating(3);
        primeWind(3);
        primeWave(3);
        primeTides(3);
    }

    private void primeRating(int retries) {
        try {
            surfliesAdapter.getRatings(SPOT_ID, INTERVAL_HOURS);
        } catch (Exception e) {
            if (retries > 0) {
                primeRating(--retries);
                return;
            }
            log.error("Get Rating to Refresh failed: ", e);
        }
    }

    private void primeWind(int retries) {
        try {
            surfliesAdapter.getWinds(SPOT_ID, INTERVAL_HOURS, CORRECTED);
        } catch (Exception e) {
            if (retries > 0) {
                primeRating(--retries);
                return;
            }
            log.error("Get Rating to Refresh failed: ", e);
        }
    }

    private void primeWave(int retries) {
        try {
            surfliesAdapter.getWaves(SPOT_ID, INTERVAL_HOURS);
        } catch (Exception e) {
            if (retries > 0) {
                primeRating(--retries);
                return;
            }
            log.error("Get Rating to Refresh failed: ", e);
        }
    }

    private void primeTides(int retries) {
        try {
            surfliesAdapter.getTides(SPOT_ID, INTERVAL_HOURS, CORRECTED);
        } catch (Exception e) {
            if (retries > 0) {
                primeRating(--retries);
                return;
            }
            log.error("Get Rating to Refresh failed: ", e);
        }
    }
}