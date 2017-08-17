package com.lffblk.restcup.tester;

import com.google.common.base.MoreObjects;

/**
 * Created by lffblk on 17.08.2017.
 */
public class GetRequest extends Request {
    public GetRequest(String host, String url) {
        super(host, url, "GET");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
