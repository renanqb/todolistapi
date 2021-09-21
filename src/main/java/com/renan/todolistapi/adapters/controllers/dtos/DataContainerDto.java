package com.renan.todolistapi.adapters.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataContainerDto {
    
    public DataContainerDto() {}

    public DataContainerDto(Object obj) {
        data = obj;
    }

    @JsonProperty("data") public Object data;
}
