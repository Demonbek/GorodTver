/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 20:25
 *
 */

package ru.gorod.tver.news_tver;

public class News {
    public String id, date, title, pic, description, text;

    public News() {
    }

    public News(String id, String date, String title, String pic, String description, String text) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.text = text;
    }
}
