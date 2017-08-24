package com.lffblk.restcup.tester;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by lffblk on 17.08.2017.
 */
public class RESTClient {

    private final static Logger LOG = LoggerFactory.getLogger(RESTClient.class);

    public void checkResponse(Response response) throws Exception {
        HttpURLConnection conn = null;
        try {
            Request request = response.getRequest();
            URL url = new URL(request.getHost() + request.getUrl());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(request.getMethod());

            if ("GET".equals(response.getRequest().getMethod())) {
                conn.setRequestProperty("Accept", "application/json");
            } else if ("POST".equals(response.getRequest().getMethod())) {
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                if (((PostRequest)response.getRequest()).getPostBody() != null) {
                    OutputStream os = conn.getOutputStream();
                    os.write(((PostRequest) response.getRequest()).getPostBody().getBytes());
                    os.flush();
                }
            }

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

            if (!expected.equals(actual)) {
                LOG.error("Incorrect response!");
                LOG.error("ER = {}", expected);
                LOG.error("AR = {}", actual);
                throw new RuntimeException("Incorrect response");
            }
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
