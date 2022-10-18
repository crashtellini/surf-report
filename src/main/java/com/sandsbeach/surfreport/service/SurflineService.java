package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.surfline.SurflineAdapter;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWaveDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWavesDto;
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
        SurfLocationReport response = new SurfLocationReport();
        String quality;
        String waveAsHumanHeight;

        // Map values and compute
        quality = convertSurflineQualityToSurfReportQuality(surflineRating.getRating().getValue());
        waveAsHumanHeight = surflineWave.getSurf().getHumanRelation();

        // Prepare output
        response.setQuality(quality);
        response.setWaveAsHumanHeight(waveAsHumanHeight);
        return response;
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
                return "The ocean dried up";
            case "1":
                return "Not worth it!";
            case "2":
                return "Not worth it.";
            case "3":
                return "Not worth it ... ";
            case "4":
                return "Not worth it ";
            case "5":
                return "Not worth it";
            default:
                return "Woah! Surfline is trippy";
        }
    }



}
