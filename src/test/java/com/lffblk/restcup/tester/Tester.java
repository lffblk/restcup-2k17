package com.lffblk.restcup.tester;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lffblk on 17.08.2017.
 */
public class Tester {
    private final static Logger LOG = LoggerFactory.getLogger(Tester.class);

    private static final String AMMO_PATH = "C:/data/ammo/";
    private static final String ANSW_PATH = "C:/data/answers/";

    public static void main(String[] args) {
        phase1();
        phase2();
        phase3();
    }

    private static void phase1() {
        LOG.info("PHASE1");
        doPhase(AMMO_PATH + "phase_1_get.ammo", ANSW_PATH + "phase_1_get.answ");
    }

    private static void phase2() {
        LOG.info("PHASE2");
        doPhase(AMMO_PATH + "phase_2_post.ammo", ANSW_PATH + "phase_2_post.answ");
    }

    private static void phase3() {
        LOG.info("PHASE3");
        doPhase(AMMO_PATH + "phase_3_get.ammo", ANSW_PATH + "phase_3_get.answ");
    }

    private static void doPhase(String requestsFileName, String answersFileName) {
        int i = 0;
        int size = 0;
        Response problemResponse = null;
        try {
            List<Request> requests = Lists.newArrayList();
            Files.lines(Paths.get(requestsFileName), StandardCharsets.UTF_8).forEach(line -> {
                Request request = null;
                if (line.startsWith("{")) {
                    PostRequest lastRequest = (PostRequest) requests.get(requests.size() - 1);
                    lastRequest.setPostBody(line);
                }
                else if (line.startsWith("GET")) {
                    request = new GetRequest("http://localhost:8080", line.substring(4, line.length() - 9));
                }
                else if (line.startsWith("POST")) {
                    request = new PostRequest("http://localhost:8080", line.substring(5, line.length() - 9));
                }
                if (request != null) {
                    requests.add(request);
                }
            });

            List<Response> responses = Lists.newArrayList();
            Iterator<Request> requestIterator = requests.iterator();
            Files.lines(Paths.get(answersFileName), StandardCharsets.UTF_8).forEach(line -> {
                Request request = requestIterator.next();
                int startCodeIndex = line.lastIndexOf(request.getUrl()) + request.getUrl().length() + 1;
                String code = line.substring(startCodeIndex, startCodeIndex + 3);
                String expectedAnswer = startCodeIndex + 4 < line.length()
                        ? line.substring(startCodeIndex + 4, line.length()) : "{}";
                Response response = new Response(expectedAnswer, Integer.parseInt(code), request);
                responses.add(response);
            });
            size = responses.size();
            LOG.debug("responses size = {}", size);

            LOG.info("START sending requests...");
            RESTClient restClient = new RESTClient();
            for (Response response : responses) {
                problemResponse = response;
                restClient.checkResponse(response);
                i++;
            }
            LOG.info("ALL RESPONSES ARE CORRECT!");
        }
        catch (Exception e) {
            LOG.error("\n\n{} requests from {} are correct", i, size);
            LOG.error("Problem response: {}", problemResponse);
            LOG.error("Exception: ", e);
            throw new RuntimeException(e);
        }
    }
}
