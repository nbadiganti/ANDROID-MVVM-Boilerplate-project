package com.driftycode.cityweatherapp.models;

/**
 * Created by nagendra on 13/11/17.
 */

public class CitiesWeatherModel {

    private int cnt;
    private WeatherModel[] list;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public WeatherModel[] getList() {
        return list;
    }

    public void setList(WeatherModel[] list) {
        this.list = list;
    }
}
