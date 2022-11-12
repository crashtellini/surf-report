package com.sandsbeach.surfreport.adapter.surfline.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surfline.dto.tide.SurflineTidesDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineTimestampData {
    private Long timestamp;

    public static final Comparator<? super SurflineTimestampData> NEAREST = (a, b) -> {
        long currentTime = System.currentTimeMillis() / 1000;
        long aDelta = Math.abs(currentTime - a.getTimestamp());
        long bDelta = Math.abs(currentTime - b.getTimestamp());

        // TODO improve this by prioritizing values in the future over the past if 7 minutes between two
        return Long.compare(aDelta, bDelta);
    };
}
