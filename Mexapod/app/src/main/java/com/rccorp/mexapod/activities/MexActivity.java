package com.rccorp.mexapod.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;


import com.rccorp.mexapod.R;

public class MexActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mex);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(mToolbar);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
