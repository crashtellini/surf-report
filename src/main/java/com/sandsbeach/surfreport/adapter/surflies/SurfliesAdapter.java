package com.sandsbeach.surfreport.adapter.surflies;


import com.sandsbeach.surfreport.adapter.surflies.dto.buoy.SurfliesBuoyDataListDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.buoy.SurfliesBuoyResponseDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.rating.SurfliesRatingsDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesResponseDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.tide.SurfliesTideListDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wave.SurfliesWavesDto;
import com.sandsbeach.surfreport.adapter.surflies.dto.wind.SurfliesWindsDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sandsbeach.surfreport.config.CacheConfig.*;

@FeignClient(name = "surflies", url = "${surflies.url}")
public interface SurfliesAdapter {

    @Cacheable(cacheNames = CACHE_NAME_SURFLIES_RATING)
    @RequestMapping(method = RequestMethod.GET,
            value = "${surflies.proxy.rating.params:/spots/forecasts/rating}")
    SurfliesResponseDto<SurfliesRatingsDto> getRatings(
            @RequestParam("spotId") String spotId,
            @RequestParam("intervalHours") int intervalHours);

    @Cacheable(cacheNames = CACHE_NAME_SURFLIES_WAVE)
    @RequestMapping(method = RequestMethod.GET,
            value = "${surflies.proxy.wave.params:/spots/forecasts/wave}")
    SurfliesResponseDto<SurfliesWavesDto> getWaves(
            @RequestParam("spotId") String spotId,
            @RequestParam("intervalHours") int intervalHours);

    @Cacheable(cacheNames = CACHE_NAME_SURFLIES_WIND)
    @RequestMapping(method = RequestMethod.GET,
            value = "${surflies.proxy.wind.params:/spots/forecasts/wind}")
    SurfliesResponseDto<SurfliesWindsDto> getWinds(
            @RequestParam("spotId") String spotId,
            @RequestParam("intervalHours") int intervalHours,
            @RequestParam("corrected") boolean corrected);

    @Cacheable(cacheNames = CACHE_NAME_SURFLIES_TIDES)
    @RequestMapping(method = RequestMethod.GET,
            value = "${surflies.proxy.tides.params:/spots/forecasts/tides}")
    SurfliesResponseDto<SurfliesTideListDto> getTides(
            @RequestParam("spotId") String spotId,
            @RequestParam("intervalHours") int intervalHours,
            @RequestParam("corrected") boolean corrected);

    @Cacheable(cacheNames = CACHE_NAME_SURFLIES_BUOY)
    @RequestMapping(method = RequestMethod.GET,
            value = "${surflies.proxy.buoy.params:/buoys/nearby?latitude=34.452&longitude=-120.78}")
    SurfliesBuoyResponseDto getBuoy();


}

