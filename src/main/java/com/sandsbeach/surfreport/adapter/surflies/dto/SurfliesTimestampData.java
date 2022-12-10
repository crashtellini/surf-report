package com.sandsbeach.surfreport.adapter.surflies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesTimestampData {
    private Long timestamp;

    public static final Comparator<? super SurfliesTimestampData> NEAREST = (a, b) -> {
        long currentTime = System.currentTimeMillis() / 1000;
        long aDelta = Math.abs(currentTime - a.getTimestamp());
        long bDelta = Math.abs(currentTime - b.getTimestamp());

        // TODO improve this by prioritizing values in the future over the past if 7 minutes between two
        return Long.compare(aDelta, bDelta);
    };
}
