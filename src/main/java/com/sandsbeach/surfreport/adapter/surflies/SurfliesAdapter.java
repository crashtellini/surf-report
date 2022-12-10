package com.sandsbeach.surfreport.adapter.surflies;


import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingsDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesResponseDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.tide.SurfliesTideListDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wave.SurfliesWavesDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wind.SurfliesWindsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "surflies", url = "${surflies.url}")
public interface SurfliesAdapter {

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/rating")
    SurfliesResponseDto<SurfliesRatingsDto> getRating(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours);

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wave")
    SurfliesResponseDto<SurfliesWavesDto> getWaves(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours);

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wind")
    SurfliesResponseDto<SurfliesWindsDto> getWinds(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours,
            @RequestParam("corrected") boolean corrected);

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/tides")
    SurfliesResponseDto<SurfliesTideListDto> getTides(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours,
            @RequestParam("corrected") boolean corrected);
}

