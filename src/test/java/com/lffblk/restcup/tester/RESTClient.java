package com.lffblk.restcup.tester;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by lffblk on 17.08.2017.
 */
public class RESTClient {

    private final static Logger LOG = LoggerFactory.getLogger(RESTClient.class);

    public void checkResponse(Response response) throws Exception {
//        LOG.debug("URL: {}", response.getRequest().getUrl());
//        LOG.debug("Expected code: {}", response.getCode());
//        LOG.debug("Expected response: {}", response.getExpectedResponse());
        if ("GET".equals(response.getRequest().getMethod())) {
            sendGetRequest(response);
        } else if ("POST".equals(response.getRequest().getMethod())) {
            sendPostRequest(response);
        }
    }

    private void sendGetRequest(Response response) throws Exception {
        Request request = response.getRequest();
        URL url = new URL(request.getHost() + request.getUrl());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(request.getMethod());
        conn.setRequestProperty("Accept", "application/json");

        int code = conn.getResponseCode();
        if (code != response.getCode()) {
            throw new RuntimeException("Failed : expected HTTP code : " + response.getCode() + ", received " + code);
        }

        if (code != 200) {
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output = br.readLine();
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> expected = (Map<String, Object>)(om.readValue(response.getExpectedResponse(), Map.class));
        Map<String, Object> actual = (Map<String, Object>)(om.readValue(output, Map.class));

        conn.disconnect();

        if (!expected.equals(actual)) {
            LOG.error("Incorrect response!");
            LOG.error("ER = {}", expected);
            LOG.error("AR = {}", actual);
            throw new RuntimeException("Incorrect response");
        }
    }

    private void sendPostRequest(Response response) {

    }
}
