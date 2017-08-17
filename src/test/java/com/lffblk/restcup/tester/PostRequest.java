package com.lffblk.restcup.tester;

/**
 * Created by lffblk on 17.08.2017.
 */
public class PostRequest extends Request {
    public PostRequest(String host, String url) {
        super(host, url, "POST");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
