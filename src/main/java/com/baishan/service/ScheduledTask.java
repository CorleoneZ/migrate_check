package com.baishan.service;

import com.baishan.controller.Check;
import com.baishan.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScheduledTask {

    @Value("${data_api.source_url}")
    private String source_url;
    @Value("${data_api.target_url}")
    private String target_url;

    @Autowired
    private Config config;

    @Autowired
    private Check check;

    public List<String> getList() {

        Map<String, List<String>> request = config.getRequest();
        List<String> list = request.get("vendor");

        return list;
    }

    @Scheduled(fixedRate = 300000)
    public void scheduledTask1() throws Exception {
        check.check(source_url, target_url, getList(), 120);
    }

   /* @Scheduled(fixedRate = 300000)
    public void scheduledTask5() {
        check.check(source_url, target_url, getList(), 5);
    }

    @Scheduled(fixedRate = 1800000)
    public void scheduledTask30() {
        check.check(source_url, target_url, getList(), 30);
    }

    @Scheduled(fixedRate = 3600000)
    public void scheduledTask60() {
        check.check(source_url, target_url, getList(), 60);
    }*/

}
