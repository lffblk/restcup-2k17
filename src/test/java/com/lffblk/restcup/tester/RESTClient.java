package com.lffblk.restcup.tester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lffblk on 17.08.2017.
 */
public class RESTClient {

    private final static Logger LOG = LoggerFactory.getLogger(RESTClient.class);

    public void checkResponse(Response response) throws Exception {
        LOG.debug("URL: {}", response.getRequest().getUrl());
        LOG.debug("Expected code: {}", response.getCode());
        LOG.debug("Expected response: {}", response.getExpectedResponse());
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

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            LOG.debug("Server Response: {}\n", output);
        }
        conn.disconnect();
    }

    private void sendPostRequest(Response response) {

    }
}
