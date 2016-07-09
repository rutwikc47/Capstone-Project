package com.rccorp.mexapod.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DownloadManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.rccorp.mexapod.MEXAPOD;
import com.rccorp.mexapod.R;
import com.rccorp.mexapod.database.apod.ApodColumns;
import com.rccorp.mexapod.database.apod.ApodCursor;
import com.rccorp.mexapod.network.ApodSync;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ApodActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,View.OnClickListener {

    ImageView apodimg;
    ImageView inv_img;

    TextView title;
    TextView copyright;
    TextView explanation;
    TextView downltext;
    TextView datetext;


    CollapsingToolbarLayout collapsingToolbarLayout;

    public static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 23;

    CoordinatorLayout coordinatorLayout;

    ProgressBar mProgress;

    private final String[] requiredPermissions = new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE };
    private static final String TAG = "ApodActivity";
    String name="ApodActivity";
    String setdate;
    String url;
    String hdurl;
    String titledownl;
    String datedownl;
    String appid;
    public static final String INTENT_KEY = "intent_key";
    public static final String INTENT_DATE = "intent_date";

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    private static final int LOADER_ID = 10;

    private Tracker mTracker;

    InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);

        inv_img=(ImageView)findViewById(R.id.gradient_imgv);
        mProgress=(ProgressBar)findViewById(R.id.apod_spinner);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coord_layout);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        downltext=(TextView)findViewById(R.id.downltext_tv);
        datetext=(TextView)findViewById(R.id.datetext_tv);
        title=(TextView)findViewById(R.id.title_tv);
        apodimg=(ImageView)findViewById(R.id.apodimg);
        copyright=(TextView)findViewById(R.id.copyright_tv);
        explanation=(TextView)findViewById(R.id.expl_tv);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
        appid=getResources().getString(R.string.banner_ad_unit_id);

        MEXAPOD application = (MEXAPOD) getApplication();
        mTracker = application.getDefaultTracker();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(appid);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

        if (savedInstanceState==null){
            getApod(INTENT_KEY,"key");
        }else {
            coordinatorLayout.setVisibility(View.VISIBLE);
            title.setText(savedInstanceState.getString("title"));
            explanation.setText(savedInstanceState.getString("expl"));
            copyright.setText(savedInstanceState.getString("copyright"));
            Glide.with(this).load(savedInstanceState.getString("url")).into(apodimg);
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName("Mexapod~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new android.content.CursorLoader(
                this,
                ApodColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data.getCount()!=0){
            String expl=getResources().getString(R.string.explanation_tv);
            mProgress.setVisibility(View.GONE);
            coordinatorLayout.setVisibility(View.VISIBLE);
            ApodCursor apodcursor=new ApodCursor(data);
            apodcursor.moveToFirst();
            title.setText(apodcursor.getTitle());
            copyright.setText(apodcursor.getCopyright());
            explanation.setText(expl+apodcursor.getExplanation());
            hdurl=apodcursor.getHdurl();
            url=apodcursor.getUrl();
            titledownl=apodcursor.getTitle();
            datedownl=apodcursor.getDate();
            Glide.with(this).load(apodcursor.getUrl()).into(apodimg);
        }else {
            restartLoader();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("title", (String) title.getText());
        outState.putString("copyright",(String) copyright.getText());
        outState.putString("url",url);
        outState.putString("expl",(String) explanation.getText());
        super.onSaveInstanceState(outState);
    }

    private void restartLoader(){
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void getApod(String intent,String value){
        ConnectivityManager connMgr = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Intent apodintent = new Intent(this, ApodSync.class);
            apodintent.putExtra(intent, value);
            this.startService(apodintent);
            this.restartLoader();
        }else {
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                if (hdurl != null) {
                    imageDownloader(hdurl);
                } else if (url != null) {
                    imageDownloader(url);
                }
                Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.fetch_apodimg), Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.RED);
                snackbar.show();
                animateFAB();
                break;
            case R.id.fab2:
                DialogFragment dialogFragment=new DateFragment();
                dialogFragment.show(getFragmentManager(), "DatePicker");
                break;
        }
    }

    public void animateFAB(){

        if (inv_img.getVisibility()==View.VISIBLE){
            inv_img.setVisibility(View.INVISIBLE);
            downltext.setVisibility(View.INVISIBLE);
            datetext.setVisibility(View.INVISIBLE);
        }else {
            inv_img.setVisibility(View.VISIBLE);
            downltext.setVisibility(View.VISIBLE);
            datetext.setVisibility(View.VISIBLE);
        }


        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;

        }
    }

    private void checkIfPermissionGranted() {
        if (ContextCompat.checkSelfPermission(ApodActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
            return;
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(ApodActivity.this,
                requiredPermissions,
                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
    }

    private boolean isValidUrl(String url) {

        if (URLUtil.isValidUrl(url)) {
            Uri uri = Uri.parse(url);
            if (url.startsWith("https://www.youtube.com/")){
                return true;
            }
        }
        return false;
    }

    public void imageDownloader(String imgurl){

        checkIfPermissionGranted();

        if (!isValidUrl(imgurl)){
            File direct = new File(Environment.getExternalStorageDirectory()
                    + "/Mexapod");

            if (!direct.exists()) {
                direct.mkdirs();
            }

            DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imgurl);
            DownloadManager.Request request = new DownloadManager.Request(
                    downloadUri);
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("APOD-"+titledownl)
                    .setDescription("Downloading APOD")
                    .setDestinationInExternalPublicDir("/Mexapod","APOD"+datedownl+".jpg");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            mgr.enqueue(request);
        }else {
            Toast.makeText(this,"Sorry No Image Available",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        this.getContentResolver().delete(ApodColumns.CONTENT_URI,null,null);
        finish();
    }

    public class DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();

            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH)-1;

            long maxd= TimeUnit.DAYS.toMillis(1);

            DatePickerDialog dpd=new DatePickerDialog(getActivity(), this,yy,mm,dd);
            dpd.getDatePicker().setMaxDate((calendar.getTimeInMillis())-maxd);
            return dpd;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int mm=monthOfYear+1;
            setdate=String.format("%02d-%02d-%02d",year,mm,dayOfMonth);
            Snackbar snackbar=Snackbar.make(coordinatorLayout,getResources().getString(R.string.fetch_apod),Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
            animateFAB();
            getApod(INTENT_DATE,setdate);
        }
    }
}
