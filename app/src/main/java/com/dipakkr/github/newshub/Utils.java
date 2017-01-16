package com.dipakkr.github.newshub;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by deepak on 16-01-2017.
 */

public final class Utils {

    private Utils(){
    }
    public static List<News> fetchLatestNews(String requesturl){
        URL url = CreateUrl(requesturl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        List<News> result = extractNewsData(jsonResponse);
        return result;

    }
    private static URL CreateUrl(String stringUrl ){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = null;
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try{
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(1000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(jsonResponse);
            }
        }catch (IOException e){
            e.printStackTrace();;
        }finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

}
