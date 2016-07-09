package com.rccorp.mexapod.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {


    @GET("/planetary/apod")
    Call<Apod> getApodDatawdate(@Query("api_key")String apikey, @Query("date")String date);

    @GET("/planetary/apod")
    Call<Apod> getApodwkey(@Query("api_key")String apikey);

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    Call <Mex> getMewdatecur(@Query("earth_date")String earthdate, @Query("api_key")String apikey);

    @GET("/mars-photos/api/v1/rovers/opportunity/photos")
    Call <Mex> getMewdateopp(@Query("earth_date")String earthdate,@Query("api_key")String apikey);

}