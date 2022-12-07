package com.sandsbeach.surfreport.adapter.surfline;


import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.SurflineResponseDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.SurflineTideListDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWavesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "surfline", url = "${surfline.url}")
public interface SurflineAdapter {

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/rating")
    SurflineResponseDto<SurflineRatingsDto> getRating(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours);

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wave")
    SurflineResponseDto<SurflineWavesDto> getWaves(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours);

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wind")
    SurflineResponseDto<SurflineWindsDto> getWinds(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours,
            @RequestParam("corrected") boolean corrected);

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/tides")
    SurflineResponseDto<SurflineTideListDto> getTides(
            @RequestParam("spotId") String spotId,
            @RequestParam("days") int days,
            @RequestParam("intervalHours") int intervalHours,
            @RequestParam("corrected") boolean corrected);
}

