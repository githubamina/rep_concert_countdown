package com.example.android.reputationstadiumtourcountdown;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Mia on 29/12/2017.
 */

public class info extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {


    private static final String REQUEST_URL =
            "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Taylor+Swift&api_key=cbfbcedc0d680df3e8209f508ae9ff84&format=json";

    private static final int LOADER_ID = 1;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.info);


        mTextView = (TextView) findViewById(R.id.biotext);


    // Get a reference to the ConnectivityManager to check state of network connectivity
    ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);

    // Get details on the currently active default data network
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

    // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(LOADER_ID, null, this);


    } else {
        // Otherwise, hide loading indicator and leave textview blank
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


    }
}




    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        //create new ConcertLoader when onCreateLoader is called
        return new ConcertLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String concerts) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        //set the textview's text to be the loaded string
        mTextView.setText(concerts);

        }


    @Override
    public void onLoaderReset(Loader<String> loader) {
    }
}