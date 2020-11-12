package com.edureka.openweathermap.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataGenerator {

    public static final String BASE_URL = "https://api.openweathermap.org";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        //If retrofit is null creating new retrofit
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}