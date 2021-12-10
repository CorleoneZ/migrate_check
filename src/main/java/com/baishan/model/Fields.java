package com.baishan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Fields implements Serializable {

    @JsonProperty("diff")
    private int diff;
}
