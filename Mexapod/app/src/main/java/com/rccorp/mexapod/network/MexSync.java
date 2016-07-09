package com.rccorp.mexapod.network;

import android.app.IntentService;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rccorp.mexapod.JsonDeserializer;
import com.rccorp.mexapod.database.mex.MexColumns;
import com.rccorp.mexapod.database.mex.MexContentValues;
import com.rccorp.mexapod.database.mex.MexSelection;
import com.rccorp.mexapod.fragments.MexFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rutwik on 02/05/16.
 */
public class MexSync extends IntentService {

    public static final String BASE_URL="https://api.nasa.gov/";

    String MEX_ROVER;
    static String API_KEY;
    public static final String INTENT_MAXSOL = "intent_maxsol";


    public MexSync() {
        super("Mexapod");
    }

    private static GsonConverterFactory buildGsonConvertor(){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Mex.class,new JsonDeserializer());
        Gson mygson=gsonBuilder.create();

        return GsonConverterFactory.create(mygson);
    }


    public void getMexwdate(String rover){
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(buildGsonConvertor())
                .build();

        MexSelection mexSelection=new MexSelection();
        mexSelection.delete(getBaseContext().getApplicationContext().getContentResolver());

        Api api=adapter.create(Api.class);

        Call <Mex> mexCall = null;
        if (rover.equals("curiosity")){
            mexCall=api.getMewdatecur("2016-07-01",API_KEY);
        }else if (rover.equals("opportunity")) {
            mexCall = api.getMewdateopp(getDate(-2),API_KEY);
        }
        if (mexCall.isExecuted()){
            Log.e("ERROR","Already executed");
        } else {
            mexCall.enqueue(new Callback<Mex>() {
                @Override
                public void onResponse(Call<Mex> call, Response<Mex> response) {
                    List<Mex>mexList=new ArrayList<Mex>();
                    if (response.body()==null){
                        Toast.makeText(getBaseContext().getApplicationContext(),"Error fetching data",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mexList=response.body().getMphoto();
                        for (int i=0;i<mexList.size();i++){
                            int minsol=mexList.get(i).getMinsol();
                            String imgsrc=mexList.get(i).getImgSrc();
                            String minearth=mexList.get(i).getEarthDate();
                            String camname=mexList.get(i).getCamName();
                            int maxsol=mexList.get(i).getMaxSol();
                            String maxearth=mexList.get(i).getMaxEarthdate();
                            writeMex(minsol,imgsrc,minearth,camname,maxsol,maxearth);
                            Log.e("ERROR", "getMexDatawdate: onSuccess"+camname);
                        }
                    }

                }

                @Override
                public void onFailure(Call<Mex> call, Throwable t) {
                    Log.e("ERROR", "getMexDatawdate: onFailure ");
                }
            });
        }
    }

    public void writeMex(int minsol,String imgsrc,String minearth,String camname,int maxsol,String maxearth){
        MexContentValues values=new MexContentValues();
        values.putMinsol(minsol);
        values.putImgsrc(imgsrc);
        values.putEarthdate(minearth);
        values.putCamname(camname);
        values.putMaxsol(maxsol);
        values.putMaxearthdate(maxearth);
        getBaseContext().getApplicationContext().getContentResolver().insert(MexColumns.CONTENT_URI,values.values());
    }

    public String getDate(int days){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DAY_OF_MONTH, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MEX_ROVER=intent.getStringExtra(MexFragment.INTENT_ROVER);
        if (MEX_ROVER!=null){
            getMexwdate(MEX_ROVER);
        }
    }
}
