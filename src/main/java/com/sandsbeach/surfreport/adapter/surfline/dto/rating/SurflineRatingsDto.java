package com.sandsbeach.surfreport.adapter.surfline.dto.rating;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurflineRatingsDto {

    private List<SurflineRatingDto> rating;

    public List<SurflineRatingDto> getRating() {
        return rating;
    }

    public void setRating(List<SurflineRatingDto> rating) {
        this.rating = rating;
    }
}
