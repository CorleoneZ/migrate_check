package com.baishan.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "domain")
public class Config {

    private Map<String, List<String>> request = new HashMap<>();

    public Map<String, List<String>> getRequest() {
        return request;
    }

    public void setRequest(Map<String, List<String>> request) {
        this.request = request;
    }
}
