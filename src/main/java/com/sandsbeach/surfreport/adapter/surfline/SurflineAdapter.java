package com.sandsbeach.surfreport.adapter.surfline;


import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.SurflineResponseDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.SurflineTideListDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWavesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "surfline", url = "${surfline.url}")
public interface SurflineAdapter {

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/rating?" +
            "spotId=5842041f4e65fad6a7708964&days=16&intervalHours=1&accesstoken=76a01cc852053203a9bea2d20404c4cd2b2b6d9e")
    SurflineResponseDto<SurflineRatingsDto> getRating();

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wave?" +
            "spotId=5842041f4e65fad6a7708964&days=16&intervalHours=1&accesstoken=76a01cc852053203a9bea2d20404c4cd2b2b6d9e")
    SurflineResponseDto<SurflineWavesDto> getWaves();

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wind?" +
            "spotId=5842041f4e65fad6a7708964&days=16&intervalHours=1&corrected=true&accesstoken=76a01cc852053203a9bea2d20404c4cd2b2b6d9e")
    SurflineResponseDto<SurflineWindsDto> getWinds();

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/tides?" +
            "spotId=5842041f4e65fad6a7708964&days=16&intervalHours=1&corrected=true&accesstoken=76a01cc852053203a9bea2d20404c4cd2b2b6d9e")
    SurflineResponseDto<SurflineTideListDto> getTides();



}
