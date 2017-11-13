package com.driftycode.cityweatherapp.base;

import android.app.Application;
import android.content.Context;

import com.driftycode.cityweatherapp.API.WeatherService;
import com.driftycode.cityweatherapp.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


/**
 * Created by nagendra on 12/11/17.
 */

public class WeatherApplication extends Application {

    private RestAdapter adapter;
    private WeatherService restInterface;

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public WeatherService initRestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        adapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL).setConverter(new GsonConverter(gson)).build();
        restInterface = adapter.create(WeatherService.class);
        return restInterface;
    }


}
