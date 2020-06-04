package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsActivity extends AppCompatActivity implements SensorEventListener{
    Map<String, String> sensorMap = new HashMap<>();
    Gson gson = new Gson();
    SharedPreferences preferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        SensorManager sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        //sensors.addAll(sm.getSensorList(Sensor.TYPE_GYROSCOPE));
        //sensors.addAll(sm.getSensorList(Sensor.TYPE_ORIENTATION));

        for(Sensor s : sensors) {
            Log.i("sensor list:", s.getName());
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
            sensorMap.put(s.getName(), "");
        }
        this.preferencias = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        sensorMap.put("llave", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor ed = preferencias.edit();
        String serializacion = gson.toJson(sensorMap);
        ed.putString("llave", serializacion);
        ed.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("tag", "config changed");
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.d("tag", "Portrait");
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            Log.d("tag", "Landscape");
        else
            Log.w("tag", "other: " + orientation);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            EntidadEvents entidadEvents = new EntidadEvents("DEV", "Sensor", "ACTIVO", "Se giró la pantalla del celular en orientación portaretrato");
            EventsService eventsService = EventsService.getInstance();

            String token = getIntent().getStringExtra("token");
            eventsService.post(token, entidadEvents);
            //sensorMap.put("pantalla", "prueba");
            sensorMap.put("se giró la pantalla con orientación", "portaretrato");
        }

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            EntidadEvents entidadEvents = new EntidadEvents("DEV", "Sensor", "ACTIVO", "Se giró la pantalla del celular en orientación paisaje");
            EventsService eventsService = EventsService.getInstance();

            String token = getIntent().getStringExtra("token");
            eventsService.post(token, entidadEvents);
            //sensorMap.put("pantalla", "prueba2");
            sensorMap.put("se giró la pantalla con orientación", "paisaje");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorMap.put(event.sensor.getName(), gson.toJson(sensorMap));
        updateValues();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updateValues() {
        TextView element = this.findViewById(R.id.json);
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : sensorMap.entrySet()) {
            sb.append("clave=" + entry.getKey() + ", valor=" + entry.getValue()+"\n");
        }

        element.setText(sb.toString());
    }

}