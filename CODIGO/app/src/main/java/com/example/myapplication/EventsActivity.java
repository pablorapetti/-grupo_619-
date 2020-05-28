package com.example.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class EventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        Log.d("tag", "config changed");
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.d("tag", "Portrait");
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            Log.d("tag", "Landscape");
        else
            Log.w("tag", "other: " + orientation);

        if(orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            EntidadEvents entidadEvents = new EntidadEvents("DEV", "Sensor", "ACTIVO", "Se giró la pantalla del celular en orientación portaretrato");
            EventsService eventsService = EventsService.getInstance();

            String token = getIntent().getStringExtra("token");
            eventsService.post(token, entidadEvents);
        }

        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            EntidadEvents entidadEvents = new EntidadEvents("DEV", "Sensor", "ACTIVO", "Se giró la pantalla del celular en orientación paisaje");
            EventsService eventsService = EventsService.getInstance();

            String token = getIntent().getStringExtra("token");
            eventsService.post(token, entidadEvents);
        }
    }
}
