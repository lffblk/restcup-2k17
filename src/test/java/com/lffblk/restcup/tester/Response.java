package com.lffblk.restcup.tester;

import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 17.08.2017.
 */
public class Response {
    private final String expectedResponse;
    private final int code;
    private final Request request;

    public Response(String expectedResponse, int code, Request request) {
        this.expectedResponse = expectedResponse;
        this.code = code;
        this.request = request;
    }

    public String getExpectedResponse() {
        return expectedResponse;
    }

    public int getCode() {
        return code;
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("expectedResponse", expectedResponse)
                .add("request", request)
                .toString();
    }
}
