package com.driftycode.cityweatherapp.API;

import com.driftycode.cityweatherapp.models.CitiesWeatherModel;
import com.driftycode.cityweatherapp.models.WeatherModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by nagendra on 12/11/17.
 */
public interface WeatherService {

    /*
     * Returns data based on the country and city name
     */
    @GET("/weather")
    void getWeatherDataByCityCode(@Query("q") String q, @Query("appid") String appId, Callback<WeatherModel> cb);

    /*
     * Returns all the data for given countries
     */
    @GET("/group")
    void getWeatherForAllCountries(@Query("id") String id, @Query("appid") String appid, Callback<CitiesWeatherModel> cb);
}