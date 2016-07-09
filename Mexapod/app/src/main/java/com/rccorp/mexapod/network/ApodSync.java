package com.rccorp.mexapod.network;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.rccorp.mexapod.activities.ApodActivity;
import com.rccorp.mexapod.database.apod.ApodColumns;
import com.rccorp.mexapod.database.apod.ApodContentValues;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Rutwik on 01/05/16.
 */

public class ApodSync extends IntentService {

    public static final String BASE_URL="https://api.nasa.gov/";

    static String API_KEY;

    String APOD_KEY;
    String APOD_DATE;

    public ApodSync(){
        super("Mexapod");

    }

    public void getApodwkey(){
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getBaseContext().getApplicationContext().getContentResolver().delete(ApodColumns.CONTENT_URI,null,null);

        Api api = adapter.create(Api.class);

        Call<Apod> call=api.getApodwkey(API_KEY);
        if (call.isExecuted()){
            Log.e("ERROR","Already executed");
        } else {
            call.enqueue(new Callback<Apod>() {
                @Override
                public void onResponse(Call<Apod> call, Response<Apod> response) {
                    if (response.body()==null){
                        Toast.makeText(getBaseContext().getApplicationContext(),"Error fetching data",Toast.LENGTH_SHORT).show();
                    }else {
                        String title=response.body().getTitle();
                        String exp=response.body().getExplanation();
                        String date=response.body().getDate();
                        String copyright=response.body().getCopyright();
                        String url=response.body().getUrl();
                        String hdurl=response.body().getHdurl();
                        if (title==null){
                            title="-";
                        }
                        if (copyright==null){
                            copyright="-";
                        }
                        if (date==null){
                            date="-";
                        }
                        if (url==null){
                            url="-";
                        }
                        if (hdurl==null){
                            hdurl="-";
                        }
                        if (exp==null){
                            exp="-";
                        }
                        writeApod(date,title,copyright,url,exp,hdurl);
                        Log.e("Success", "getApodDatawkey: onResponse " + response.body().getTitle());
                    }
                }

                @Override
                public void onFailure(Call<Apod> call, Throwable t) {
                    Log.e("ERROR", "getApodDatawkey: onFailure ");
                }
            });
        }

    }

    public void getApodwdate(String reqdate){
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = adapter.create(Api.class);

        getBaseContext().getApplicationContext().getContentResolver().delete(ApodColumns.CONTENT_URI,null,null);

        Call<Apod> call=api.getApodDatawdate(API_KEY,reqdate);
        if (call.isExecuted()){
            Log.e("ERROR","Already executed");
        } else {
            call.enqueue(new Callback<Apod>() {
                @Override
                public void onResponse(Call<Apod> call, Response<Apod> response) {
                    if (response.body()==null){
                        Toast.makeText(getBaseContext().getApplicationContext(),"Error fetching data",Toast.LENGTH_SHORT).show();
                    }else {
                        String title=response.body().getTitle();
                        String exp=response.body().getExplanation();
                        String date=response.body().getDate();
                        String copyright=response.body().getCopyright();
                        String url=response.body().getUrl();
                        String hdurl=response.body().getHdurl();
                        if (title==null){
                            title="-";
                        }
                        if (copyright==null){
                            copyright="-";
                        }
                        if (date==null){
                            date="-";
                        }
                        if (url==null){
                            url="-";
                        }
                        if (hdurl==null){
                            hdurl="-";
                        }
                        if (exp==null){
                            exp="-";
                        }
                        writeApod(date,title,copyright,url,exp,hdurl);
                        Log.e("Success", "getApodDatawdate: onResponse " + response.body().getTitle());
                    }
                }

                @Override
                public void onFailure(Call<Apod> call, Throwable t) {
                    Log.e("ERROR", "getApodDatawdate: onFailure ");
                }
            });
        }
    }

    private void writeApod(String date, String title,String copyright, String url, String explanation, String hdurl) {
        ApodContentValues values=new ApodContentValues();
        values.putDate(date);
        values.putTitle(title);
        values.putCopyright(copyright);
        values.putUrl(url);
        values.putExplanation(explanation);
        values.putHdurl(hdurl);
        getBaseContext().getApplicationContext().getContentResolver().insert(ApodColumns.CONTENT_URI,values.values());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        APOD_KEY=intent.getStringExtra(ApodActivity.INTENT_KEY);
        APOD_DATE=intent.getStringExtra(ApodActivity.INTENT_DATE);
        if (APOD_KEY!=null){
            getApodwkey();
        }else if (APOD_DATE!=null){
            getApodwdate(APOD_DATE);
        }
    }
}







