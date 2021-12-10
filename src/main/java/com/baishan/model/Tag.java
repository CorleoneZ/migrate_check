package com.baishan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Tag implements Serializable {

    @JsonProperty("domain")
    private String domain;
}
