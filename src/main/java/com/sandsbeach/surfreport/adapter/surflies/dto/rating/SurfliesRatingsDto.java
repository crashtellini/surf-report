package com.sandsbeach.surfreport.adapter.surflies.dto.rating;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesRatingsDto {

    private List<SurfliesRatingDto> rating;

    public List<SurfliesRatingDto> getRating() {
        return rating;
    }

    public void setRating(List<SurfliesRatingDto> rating) {
        this.rating = rating;
    }
}
