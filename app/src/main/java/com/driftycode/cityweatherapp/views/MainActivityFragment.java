package com.driftycode.cityweatherapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.driftycode.cityweatherapp.API.WeatherService;
import com.driftycode.cityweatherapp.Adapters.CountriesListAdapter;
import com.driftycode.cityweatherapp.R;
import com.driftycode.cityweatherapp.base.WeatherApplication;
import com.driftycode.cityweatherapp.models.CitiesWeatherModel;
import com.driftycode.cityweatherapp.models.WeatherModel;
import com.driftycode.cityweatherapp.utils.Constants;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static Activity activity;
    private WeatherService restInterface;
    private String TAG = "MainFragment";
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private WeatherModel[] weatherModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final WeatherApplication weatherApplication = (WeatherApplication) getActivity().getApplication();
        restInterface = weatherApplication.initRestClient();

        mRecyclerView = view.findViewById(R.id.rv_countries);
        mRecyclerView.setHasFixedSize(true);
        activity = getActivity();

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        restInterface.getWeatherForAllCountries(Constants.DEFAULT_COUNTRYCODES, Constants.API_KEY, new Callback<CitiesWeatherModel>() {
            @Override
            public void success(CitiesWeatherModel citiesWeatherModel, Response response) {
                Log.d(TAG, "Size for weather object" + citiesWeatherModel.getList().length);
                if (citiesWeatherModel.getList().length > 0) {
                    weatherModels = citiesWeatherModel.getList();
                    generateCountryCards(citiesWeatherModel);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error ");
                error.printStackTrace();
            }
        });
        return view;
    }

    private void generateCountryCards(CitiesWeatherModel citiesWeatherModel) {
        mAdapter = new CountriesListAdapter(activity, citiesWeatherModel.getList());
        mRecyclerView.setAdapter(mAdapter);

    }

}
