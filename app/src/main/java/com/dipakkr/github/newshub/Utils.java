package com.dipakkr.github.newshub;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
                jsonResponse = readFromStream(inputStream);
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
    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    private static  List<News> extractNewsData(String newsJson){

        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }
        ArrayList<News> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(newsJson);
            JSONArray articles = jsonObject.getJSONArray("articles");

            for (int i =0; i<articles.length(); i++){
                JSONObject newfeedjson = articles.getJSONObject(i);

                String detail= newfeedjson.getString("title");
                String Url = newfeedjson.getString("url");

                News news = new News(detail,Url);
                arrayList.add(news);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
