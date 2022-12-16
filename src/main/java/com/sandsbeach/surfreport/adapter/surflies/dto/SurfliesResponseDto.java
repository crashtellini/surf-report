package com.sandsbeach.surfreport.adapter.surflies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SurfliesResponseDto<T> {
    // TODO: Get lat long from this object and create DTO classes for it
    // private Map<String, String> associated;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
