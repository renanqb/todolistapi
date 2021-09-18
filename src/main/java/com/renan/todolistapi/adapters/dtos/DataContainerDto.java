package com.renan.todolistapi.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataContainerDto {
    
    public DataContainerDto(Object obj) {
        data = obj;
    }

    @JsonProperty("data") protected Object data;
}
