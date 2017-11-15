package com.driftycode.cityweatherapp.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.driftycode.cityweatherapp.service.models.CitiesWeatherModel;
import com.driftycode.cityweatherapp.service.models.WeatherModel;
import com.driftycode.cityweatherapp.utils.Constants;

import retrofit2.Call;

/**
 * Created by nagendra on 16/11/17.
 */

public class ProjectViewModel extends ViewModel {

    private Call<CitiesWeatherModel> countriesListObservable;
    private Call<WeatherModel> countryWeatherObservable;

    public ProjectViewModel() {
        countriesListObservable = null;
        countryWeatherObservable = null;
    }

    public Call<CitiesWeatherModel> getCountriesListObservable() {

        countriesListObservable = ProjectRepository.getInstance().getWeatherForAllCountries(Constants.DEFAULT_COUNTRYCODES, Constants.API_KEY);
        return countriesListObservable;
    }

    public Call<WeatherModel> getWeatherByCountry(String countryID) {
        countryWeatherObservable = ProjectRepository.getInstance().getWeatherDataByCityCode(countryID, Constants.API_KEY);
        return countryWeatherObservable;
    }


}
