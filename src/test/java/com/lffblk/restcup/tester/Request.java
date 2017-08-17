package com.lffblk.restcup.tester;

import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 17.08.2017.
 */
public abstract class Request {
    private final String host;
    private final String url;
    private final String method;

    public Request(String host, String url, String method) {
        this.host = host;
        this.url = url;
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("host", host)
                .add("url", url)
                .add("method", method)
                .toString();
    }
}
