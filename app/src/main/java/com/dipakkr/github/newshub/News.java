package com.dipakkr.github.newshub;

/**
 * Created by deepak on 16-01-2017.
 */

public class News {
    private final String mdetail;
    private final String mUrl;

    public  News(String detail,String Url){
        this.mdetail=detail;
        this.mUrl = Url;
    }

    public String getmdetail() {
        return mdetail;
    }

    public String getmUrl() {
        return mUrl;
    }
}
