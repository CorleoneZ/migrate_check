package com.baishan.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    public static final String CONTENT_TYPE = "Content-Type";

    public String doPost(String url, String json) throws Exception {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String data;

        try {
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            data = (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) ? EntityUtils.toString(res.getEntity()) : null;
            /*JSONObject jsonObject = JSONObject.parseObject(data);*/
            /*result = String.valueOf(jsonObject);*/
        } finally {
            client.close();
        }
        return data;
    }

}
