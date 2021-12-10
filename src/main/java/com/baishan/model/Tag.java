package com.baishan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {

    @JsonProperty("domain")
    private String domain;
}
