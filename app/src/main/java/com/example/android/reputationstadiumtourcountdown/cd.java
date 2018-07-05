package com.example.android.reputationstadiumtourcountdown;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mia on 28/12/2017.
 */

public class cd extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);

    final TextView tv_countdown = (TextView) findViewById(R.id.timeLeft);

    Calendar start_calendar = Calendar.getInstance();
    Calendar end_calendar = Calendar.getInstance();
        end_calendar.set(2018, 10, 2, 19, 0); // 10 = November, month start at 0 = January

    long start_millis = start_calendar.getTimeInMillis(); //get the start time in milliseconds
    long end_millis = end_calendar.getTimeInMillis(); //get the end time in milliseconds
    long total_millis = (end_millis - start_millis); //total time in milliseconds


    //1000 = 1 second interval
    CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
            millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

            long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
            millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

            long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
            millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

            tv_countdown.setText(days + " days \n" + hours + " hours \n" + minutes + " minutes \n" + seconds + " seconds "); //You can compute the millisUntilFinished on hours/minutes/seconds
        }

        @Override
        public void onFinish() {
            tv_countdown.setText("Finish!");
        }
    };
    cdt.start();


        String url = "http://premier.ticketek.com.au/shows/show.aspx?sh=TAYLORSW18";

        final Uri webpage = Uri.parse(url);


        Button button = (Button) findViewById(R.id.ticketek);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tikIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(tikIntent);
            }
        });

        ImageView map = (ImageView) findViewById(R.id.map);

        final Uri mapUri = Uri.parse("geo:0,0?q=ANZ Stadium");

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                if (mapIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapIntent);
                } else{
                    Log.d("cd", "can't handle this intent!");
                }
            }
        });

        ImageView mip = (ImageView) findViewById(R.id.mapp);

        final Uri mipUri = Uri.parse("https://d35kvm5iuwjt9t.cloudfront.net/hotshow/MARIANNA/SeatingMap.pdf");

        mip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mipIntent = new Intent(Intent.ACTION_VIEW, mipUri);
                startActivity(mipIntent);
            }
        });
}



    //Inflates menu layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Opens settings activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, info.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
