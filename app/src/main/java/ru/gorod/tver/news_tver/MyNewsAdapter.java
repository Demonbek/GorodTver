/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 20:25
 *
 */

package ru.gorod.tver.news_tver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.gorod.tver.R;



public class MyNewsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<News> objects;

    MyNewsAdapter(Context context, ArrayList<News> news) {
        ctx = context;
        objects = news;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.news_item, parent, false);
        }

        News n = getNews(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.newsData)).setText(n.date);
        ((TextView) view.findViewById(R.id.newsTitle)).setText(n.title);
        ImageView newsPic = view.findViewById(R.id.newsPic);
        Picasso.get().load(n.pic).error(R.drawable.error).into(newsPic);

        return view;
    }

    // товар по позиции
    News getNews(int position) {
        return ((News) getItem(position));
    }
}
