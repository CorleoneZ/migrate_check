package com.baishan.controller;

import com.alibaba.fastjson.JSON;
import com.baishan.model.Fields;
import com.baishan.model.Metric;
import com.baishan.model.Tag;
import com.baishan.service.HttpClient;
import com.baishan.util.ExecShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * sudo -u flume hadoop distcp -update -async hdfs://10.101.16.170:8020/separated/test.dl.qingcdn.com/$ltime*vendor*.gz
 * hdfs://10.104.3.41:8020/separated/test.dl.qingcdn.com/
 */

@Service
public class Check {

    private static final Logger logger = LoggerFactory.getLogger(Check.class);
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    @Autowired
    private HttpClient client;

    public void check(String source_url, String target_url, List<String> domains, int dur) throws Exception {

        LocalDateTime now = LocalDateTime.now();
        String startTime = now.minusMinutes(dur).format(timeFormatter);

        for (String domain : domains) {

            String check_source = String.format("hadoop fs -ls hdfs://%s/separated/%s/%s.*vendor*.gz |wc -l",source_url , domain, startTime);
            logger.info("check source shell: " + check_source);
            String[] shell1 = new String[]{"/bin/bash", "-c", check_source};
            String source_count = ExecShell.doShell(shell1);
            String check_target = String.format("hadoop fs -ls hdfs://%s/separated/%s/%s.*vendor*.gz |wc -l",target_url , domain, startTime);
            logger.info("check target shell: " + check_target);
            String[] shell2 = new String[]{"/bin/bash", "-c", check_target};
            String target_count = ExecShell.doShell(shell2);

            /* add pinyun log*/
            //hadoop fs -ls hdfs://10.104.3.41:8020/separated/media.st.dl.pinyuncloud.com/202112101100.*vendor_pinyun*.gz |wc -l
            int add = 0;
            if (domain.equals("media.st.dl.pinyuncloud.com")) {
                String check_add = String.format("hadoop fs -ls hdfs://%s/separated/%s/%s.*vendor_pinyun*.gz |wc -l",target_url , domain, startTime);
                logger.info("check add shell: " + check_add);
                String[] shell3 = new String[]{"/bin/bash", "-c", check_add};
                String add_count = ExecShell.doShell(shell3);
                add = Integer.parseInt(add_count);
            }

            int s = Integer.parseInt(source_count);
            int t = Integer.parseInt(target_count);

            long timestamp = System.currentTimeMillis() / 1000;
            if (s == t - add) {
                logger.info("OK same count...  domain: " + domain + "  source: " + s + "   target: " + t);

                /*发送监控指标*/
                Tag tag = Tag.builder().domain(domain).build();
                Fields fields = Fields.builder().diff(0).build();
                Metric metric = Metric.builder().name("bigdata_log_migration_check").value(0).fields(fields).time(timestamp).endpoint(null).tags(tag).step(3000).build();
                List<Metric> list = new ArrayList<Metric>();
                list.add(metric);
                String param = JSON.toJSONString(list);
                String res = client.doPost("http://127.0.0.1:10699/v2/push", param);
                logger.info("res: " + res + " param" + String.valueOf(list));
            } else {
                logger.info("Error check source and target are not same...  domain: " + domain + "start time: " + startTime + " +source: " + s + "   target: " + t);

                /*发送监控指标*/
                Tag tag = Tag.builder().domain(domain).build();
                Fields fields = Fields.builder().diff(Math.abs(t-add-s)).build();
                Metric metric = Metric.builder().name("bigdata_log_migration_check").value(Math.abs(t-add-s)).fields(fields).time(timestamp).endpoint(null).tags(tag).step(3000).build();
                List<Metric> list = new ArrayList<Metric>();
                list.add(metric);
                String param = JSON.toJSONString(list);
                String res = client.doPost("http://127.0.0.1:10699/v2/push", param);
                logger.info("res: " + res + " param" + String.valueOf(list));
            }
        }
    }
}
