package com.example.watchudrive;

import android.content.Context;

import java.util.ArrayList;

public class ApiCalls {

private Context mContext;
private ArrayList<NewsFeedItems> newsFeedItems;
private final static String URL_HEAD = "https://witty-bird-5.localtunnel.me";
private final static String URL_TAIL = "/api/feed_items";
private static String URL_NEWS_FEED;

    public ApiCalls(Context mContext) {
        this.mContext = mContext;
    }

    public static String getURL_NEWS_FEED() {
        URL_NEWS_FEED = (URL_HEAD + URL_TAIL);
        return URL_NEWS_FEED;
    }

    public ArrayList<NewsFeedItems> getNewsFeedItems() {
        return newsFeedItems;
    }
}
