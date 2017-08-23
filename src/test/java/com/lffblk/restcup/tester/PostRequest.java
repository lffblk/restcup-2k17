package com.lffblk.restcup.tester;

/**
 * Created by lffblk on 17.08.2017.
 */
public class PostRequest extends Request {
    private String postBody;

    public PostRequest(String host, String url) {
        super(host, url, "POST");
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPostBody = " + postBody;
    }
}
