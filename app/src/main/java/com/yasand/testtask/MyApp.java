package com.yasand.testtask;

import android.app.Application;

import com.yasand.testtask.Api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application{

    private static Api api;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit=new Retrofit.Builder()
                .baseUrl("http://kot3.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api=retrofit.create(Api.class);
    }

    public static Api getApi()
    {
        return api;
    }

}
