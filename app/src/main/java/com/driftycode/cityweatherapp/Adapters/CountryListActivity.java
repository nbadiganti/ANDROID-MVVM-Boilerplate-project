package com.driftycode.cityweatherapp.Adapters;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.driftycode.cityweatherapp.R;

/**
 * Created by nagendra on 13/11/17.
 */

public class CountryListActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.country_list_activity);

    }
}
