package com.driftycode.cityweatherapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.driftycode.cityweatherapp.base.WeatherApplication;
import com.driftycode.cityweatherapp.service.API.WeatherService;
import com.driftycode.cityweatherapp.service.models.CitiesWeatherModel;
import com.driftycode.cityweatherapp.service.models.WeatherModel;
import com.driftycode.cityweatherapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nagendra on 16/11/17.
 */

public class ProjectRepository {

    private static WeatherService weatherService;

    public static WeatherService getInstance() {
        weatherService = WeatherApplication.initRestClient();
        return weatherService;
    }

    /*
     * Method: getWeathersForCountries
     * It fetches the data from HTTP API and convert into observable livedata object
     */
    public LiveData<CitiesWeatherModel> getWeathersForCountries() {
        final MutableLiveData<CitiesWeatherModel> data = new MutableLiveData<>();

        weatherService.getWeatherForAllCountries(Constants.DEFAULT_COUNTRYCODES, Constants.API_KEY).enqueue(new Callback<CitiesWeatherModel>() {
            @Override
            public void onResponse(@NonNull Call<CitiesWeatherModel> call, @NonNull Response<CitiesWeatherModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CitiesWeatherModel> call, @NonNull Throwable t) {

            }
        });

        return data;
    }

    /*
     * Method: getWeatherDataByCityCode
     * It fetches the data from HTTP API based on the country name - convert into observable livedata object
     */
    public LiveData<WeatherModel> getWeatherDataByCityCode(String q) {
        final MutableLiveData<WeatherModel> data = new MutableLiveData<>();

        weatherService.getWeatherDataByCityCode(q, Constants.API_KEY).enqueue(new Callback<WeatherModel>() {

            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {

            }
        });

        return data;
    }
}
