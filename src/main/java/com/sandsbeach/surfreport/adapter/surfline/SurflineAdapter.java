package com.sandsbeach.surfreport.adapter.surfline;


import com.sandsbeach.surfreport.adapter.surfline.dto.rating.SurflineRatingsDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.SurflineResponseDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wave.SurflineWavesDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindDto;
import com.sandsbeach.surfreport.adapter.surfline.dto.wind.SurflineWindsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.geom.RectangularShape;

@FeignClient(name = "surfline", url = "${surfline.url}")
public interface SurflineAdapter {

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/rating?" +
            "spotId=5842041f4e65fad6a7708964&days=1&intervalHours=1&correctedWind=false&accesstoken=61e082dfaccf57480af2c13efa2cb409d515c33e")
    SurflineResponseDto<SurflineRatingsDto> getRating();

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wave?" +
            "spotId=5842041f4e65fad6a7708964&days=16&intervalHours=1&accesstoken=61e082dfaccf57480af2c13efa2cb409d515c33e")
    SurflineResponseDto<SurflineWavesDto> getWaves();

    @RequestMapping(method = RequestMethod.GET, value = "/spots/forecasts/wind?" +
            "spotId=5842041f4e65fad6a7708964&days=16&intervalHours=1&corrected=true&accesstoken=61e082dfaccf57480af2c13efa2cb409d515c33e")
    SurflineResponseDto<SurflineWindsDto> getWinds();
}
