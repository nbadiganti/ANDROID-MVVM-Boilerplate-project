package com.driftycode.cityweatherapp.base;

import android.app.Application;
import android.content.Context;

import com.driftycode.cityweatherapp.service.API.WeatherService;
import com.driftycode.cityweatherapp.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by nagendra on 12/11/17.
 */

public class WeatherApplication extends Application {

    public static WeatherService initRestClient() {

        // GSON converter factory for Retrofit response
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit adapter = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return adapter.create(WeatherService.class);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

}
