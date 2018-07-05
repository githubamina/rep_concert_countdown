package com.example.android.reputationstadiumtourcountdown;

/**
 * Created by Mia on 29/12/2017.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;


public class ConcertLoader extends AsyncTaskLoader<String> {


    private static final String LOG_TAG = ConcertLoader.class.getName();

    //query url string
    private String mUrl;

    /**
     * Constructs a new ConcertLoader}.
     * @param context of the activity
     * @param url to load data from
     */
    public ConcertLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public String loadInBackground() {
        //Return early if no url is provided
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract artist bio
        String artistinfo = QueryUtils.fetchData(mUrl);

        //return the artist bio
        return artistinfo;
    }
}
