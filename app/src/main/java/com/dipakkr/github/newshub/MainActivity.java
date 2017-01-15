package com.dipakkr.github.newshub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String REQUEST_URL =
            "https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=latest&apiKey=88e1a317ddc5481a87dcea6b48a9204c";
    private ArrayAdapter<News> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(REQUEST_URL);

        ListView listView = (ListView)findViewById(R.id.list_main);
        mAdapter = new NewsAdapter(this, new  ArrayList<News>());
        listView.setAdapter(mAdapter);
    }
    private class NewsAsyncTask extends AsyncTask<String,Void,List<News>>{

        @Override
        protected List<News> doInBackground(String... strings) {
            List<News> result = Utils.fetchLatestNews(REQUEST_URL);
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<News> newses) {
            super.onPostExecute(newses);
        }
    }
}
