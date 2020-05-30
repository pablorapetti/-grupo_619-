package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventsActivity extends AppCompatActivity {
    List<EventSensor> lista;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String json = preferencias.getString("llave", null);

        if(json!=null){
            lista = Arrays.asList(gson.fromJson(json, EventSensor[].class));
        }
        else{
            lista = new ArrayList<>();
        }

        for(EventSensor e:lista){
            Log.i("probando", e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = preferencias.edit();
        String serializacion = gson.toJson(lista);
        ed.putString("llave", serializacion);
        ed.commit();
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
            lista.add(new EventSensor("pantalla", "prueba"));
        }

        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            EntidadEvents entidadEvents = new EntidadEvents("DEV", "Sensor", "ACTIVO", "Se giró la pantalla del celular en orientación paisaje");
            EventsService eventsService = EventsService.getInstance();

            String token = getIntent().getStringExtra("token");
            eventsService.post(token, entidadEvents);
            lista.add(new EventSensor("pantalla", "prueba2"));
        }


    }
}
