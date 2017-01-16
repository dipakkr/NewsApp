package com.dipakkr.github.newshub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by deepak on 16-01-2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> news){
        super(context,0,news);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newsItemView = convertView;
        if(newsItemView == null){
            newsItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.new_items,parent,false);

            News newsList = getItem(position);
            TextView newsTitle = (TextView)newsItemView.findViewById(R.id.news_title);

            newsTitle.setText(newsList.getmdetail());
        }
        return newsItemView;

    }
}
