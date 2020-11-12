package com.edureka.openweathermap.rest;

import com.edureka.openweathermap.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherClient {

    //GET
    @GET("/data/2.5/weather")
    Call<WeatherData> getdata(@Query("q") String city_name , @Query("AppID") String key);

}
