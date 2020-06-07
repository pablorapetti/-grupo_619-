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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsActivity extends AppCompatActivity implements SensorEventListener{
    private long delayOrientation=-1;
    private long delayLight=-1;
    private long delayProx=-1;
    private long timeDelay=2000;
    private float sensorlight = -1;
    private float sensorprox = -1;
    private float orientationX = -1;
    private float orientationY = -1;
    private float orientationZ = -1;

    List<EventSensor> events = new ArrayList<>();
    Gson gson = new Gson();
    SharedPreferences preferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        SensorManager sm = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = new ArrayList<>();
        sensors.add(sm.getDefaultSensor(Sensor.TYPE_ORIENTATION));
        sensors.add(sm.getDefaultSensor(Sensor.TYPE_LIGHT));
        sensors.add(sm.getDefaultSensor(Sensor.TYPE_PROXIMITY));

        this.preferencias = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        EventSensor[] a = gson.fromJson(preferencias.getString("llave", "[]"),EventSensor[].class);
        Collections.addAll(events, a);

        ListView lista = findViewById(R.id.listView);
        ListaAdapter adapter = new ListaAdapter(this.getApplicationContext(), -1, events);
        lista.setAdapter(adapter);


        //List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ORIENTATION);
        //sensors.addAll(sm.getSensorList(Sensor.TYPE_LIGHT));
        //sensors.addAll(sm.getSensorList(Sensor.TYPE_PROXIMITY));

        for(Sensor s : sensors) {
            Log.i("sensor list:", s.getName());
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor ed = preferencias.edit();
        String serializacion = gson.toJson(events);
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
            //events.put("se giró la pantalla con orientación", "portaretrato");
        }

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            EntidadEvents entidadEvents = new EntidadEvents("DEV", "Sensor", "ACTIVO", "Se giró la pantalla del celular en orientación paisaje");
            EventsService eventsService = EventsService.getInstance();

            String token = getIntent().getStringExtra("token");
            eventsService.post(token, entidadEvents);
            //sensorMap.put("pantalla", "prueba2");
            //sensorMap.put("se giró la pantalla con orientación", "paisaje");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sync(event);


        //sensorMap.put(event.sensor.getName(), gson.toJson(sensorMap));
        //updateValues();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void updateValues() {
        ListView lista = findViewById(R.id.listView);
        ((ListaAdapter) lista.getAdapter()).clear();
        ((ListaAdapter) lista.getAdapter()).addAll(events);
        ((ListaAdapter) lista.getAdapter()).notifyDataSetChanged();
    }

    private synchronized void addEvent(SensorEvent ev, String name, boolean orientation){
        ListView lista = findViewById(R.id.listView);
        EventSensor evento = new EventSensor(ev.sensor.getName(), "se ejecutó este evento");

        if(orientation){
            evento.setDesc("value: z: "+ev.values[0]+" x:"+ev.values[1]+" y:"+ev.values[2]);
        }
        else{
            evento.setDesc("value: "+ev.values[0]);
        }

        evento.setName(name);
        //events.add(evento);
        ((ListaAdapter) lista.getAdapter()).add(evento);
        //updateValues();
    }

    private synchronized void sync(SensorEvent event){
        if(delayOrientation==-1){
            delayOrientation = System.currentTimeMillis();
        }
        long diffOr = System.currentTimeMillis() - delayOrientation;

        if(diffOr>=timeDelay){
            delayOrientation = System.currentTimeMillis();


            if(orientationX != event.values[1]){
                Log.i("orientacion", "por aca " +event.values.length);
                addEvent(event, "sensor orientacion", true);
                orientationX = event.values[1];
            }

            if(orientationY != event.values[2]){
                Log.i("orientacion", "por aca " +event.values.length);
                addEvent(event, "sensor orientacion", true);
                orientationY = event.values[2];
            }

            if(orientationZ != event.values[0]){
                Log.i("orientacion", "por aca " +event.values.length);
                addEvent(event, "sensor orientacion", true);
                orientationZ = event.values[0];
            }
        }



        if(delayLight==-1){
            delayLight = System.currentTimeMillis();
        }
        long diff = System.currentTimeMillis() - delayLight;

        if(diff>=(timeDelay+200)){
            delayLight = System.currentTimeMillis();

            if(sensorlight != event.values[0]){
                //Log.i("luz", "por aca " +event.values[0]);
                addEvent(event, "sensor luz", false);

                sensorlight = event.values[0];
            }

        }



        if(delayProx==-1){
            delayProx = System.currentTimeMillis();
        }
        long diffProx = System.currentTimeMillis() - delayProx;

        if(diffProx>=(timeDelay+400)){
            delayProx = System.currentTimeMillis();

            if(sensorprox != event.values[0]){
                //Log.i("prox", "por aca " +event.values[0]);
                addEvent(event, "sensor prox", false);

                sensorprox = event.values[0];
            }

        }
    }
}