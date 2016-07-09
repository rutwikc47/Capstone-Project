package com.rccorp.mexapod.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.MobileAds;
import com.rccorp.mexapod.R;

public class MainActivity extends AppCompatActivity {


    ImageButton apodbt;
    ImageButton mexbt;

    public static boolean IS_TABLET = false;

    public static final String INTENT_KEY_APOD = "intent_key_apod";
    public static final String INTENT_KEY_ME = "intent_key_me";
    String appid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IS_TABLET=isTablet();
        if (IS_TABLET){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        Toolbar mtoolbar=(Toolbar)findViewById(R.id.toolbarmain);
        mtoolbar.setTitle(getTitle());
        int color= Color.parseColor("#FFFFFF");
        mtoolbar.setTitleTextColor(color);
        appid=getResources().getString(R.string.banner_ad_unit_id);

        MobileAds.initialize(getApplicationContext(),appid);


        apodbt=(ImageButton) findViewById(R.id.apod_imgb);
        mexbt=(ImageButton)findViewById(R.id.mex_imgb);

        apodbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(MainActivity.this,ApodActivity.class);
                mIntent.putExtra(INTENT_KEY_APOD,"apod");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(mIntent);
                }
            }
        });

        mexbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(MainActivity.this,MexActivity.class);
                mIntent.putExtra(INTENT_KEY_ME,"me");
                startActivity(mIntent);
            }
        });

    }

    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


}
