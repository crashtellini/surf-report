package com.sandsbeach.surfreport.adapter.surflies.dto.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sandsbeach.surfreport.adapter.surflies.dto.SurfliesTimestampData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesRatingDto extends SurfliesTimestampData {
    private Long timestamp;
    private Integer utcOffset;
    private SurfliesKeyValueDto rating;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public SurfliesKeyValueDto getRating() {
        return rating;
    }

    public void setRating(SurfliesKeyValueDto rating) {
        this.rating = rating;
    }
}
