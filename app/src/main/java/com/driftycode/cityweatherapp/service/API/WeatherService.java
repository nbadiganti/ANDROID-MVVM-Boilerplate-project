package com.driftycode.cityweatherapp.service.API;


import com.driftycode.cityweatherapp.service.models.CitiesWeatherModel;
import com.driftycode.cityweatherapp.service.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nagendra on 12/11/17.
 */
public interface WeatherService {

    /*
     * Returns data based on the country and city name
     */
    @GET("/data/2.5/weather")
    Call<WeatherModel> getWeatherDataByCityCode(@Query("q") String q, @Query("appid") String appId);

    /*
     * Returns all the data for given countries
     */
    @GET("/data/2.5/group")
    Call<CitiesWeatherModel> getWeatherForAllCountries(@Query("id") String id, @Query("appid") String appid);
}