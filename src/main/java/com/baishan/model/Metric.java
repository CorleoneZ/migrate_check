package com.baishan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Metric implements Serializable {

    @JsonProperty(value = "endpoint")
    private String endpoint;

    @JsonProperty(value = "step")
    private long step;

    @JsonProperty(value = "tags")
    private Tag tags;

    @JsonProperty(value = "value")
    private int value;

    @JsonProperty(value = "fields")
    private Fields fields;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "time")
    private long time;
}
