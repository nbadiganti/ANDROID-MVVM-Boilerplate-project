package com.driftycode.cityweatherapp.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.driftycode.cityweatherapp.API.WeatherService;
import com.driftycode.cityweatherapp.R;
import com.driftycode.cityweatherapp.base.WeatherApplication;
import com.driftycode.cityweatherapp.models.WeatherModel;
import com.driftycode.cityweatherapp.utils.Constants;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherDetailActivity extends AppCompatActivity {

    TextView tv_cityname, tv_temp, tv_climate_description;
    ImageView weather_icon;
    private String TAG = "WeatherDetailActivity";
    private WeatherService restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        tv_cityname = (TextView) findViewById(R.id.tv_cityname);
        tv_temp = (TextView) findViewById(R.id.tv_temp);
        tv_climate_description = (TextView) findViewById(R.id.tv_climate_description);
        weather_icon = (ImageView) findViewById(R.id.weather_icon);

        WeatherApplication weatherApp = (WeatherApplication) getApplication();
        restInterface = weatherApp.initRestClient();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String countryName = extras.getString("countryName");
            Log.d(TAG, "Country Name " + countryName);
            tv_cityname.setText(countryName);
            loadCityData(countryName);
        }
    }

    public void loadCityData(String countryName) {
        restInterface.getWeatherDataByCityCode(countryName, Constants.API_KEY, new retrofit.Callback<WeatherModel>() {
            @Override
            public void success(WeatherModel weatherModel, Response response) {
                Log.d(TAG, "weatherModel : " + weatherModel.getName());
                generateUI(weatherModel);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error ");
                error.printStackTrace();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void generateUI(WeatherModel weatherModel) {
        tv_temp.setText(weatherModel.getMain().getTemp() + "");
        tv_climate_description.setText(weatherModel.getWeather()[0].getMain() + " " + weatherModel.getWeather()[0].getDescription());
        loadImageFromUrlToImageView(weather_icon, weatherModel.getWeather()[0].getIcon());
    }

    private void loadImageFromUrlToImageView(final ImageView imageView, String imageIconName) {

        if (imageIconName != null) {
            String imageUri = Constants.IMAGE_URL + imageIconName + ".png";
            Log.d(TAG, "ImageURL" + imageUri);

            // To load the image dynamically on-fly
            Glide.with(this).load(imageUri)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

        } else {
            Log.e(TAG, "Image ICON NOT FOUND");
        }
    }
}
