package com.sandsbeach.surfreport.adapter.surfline.dto.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineRatingDto {
    private Long timestamp;
    private Integer utcOffset;
    private SurflineKeyValueDto rating;

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

    public SurflineKeyValueDto getRating() {
        return rating;
    }

    public void setRating(SurflineKeyValueDto rating) {
        this.rating = rating;
    }
}
