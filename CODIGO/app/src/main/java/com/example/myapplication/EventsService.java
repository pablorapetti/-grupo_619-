package com.example.myapplication;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsService {
    private static EventsService instance = null;
    private EventsInterface publicacion;
    private Retrofit retrofit;

    private EventsService() {
        retrofit = new Retrofit.Builder().baseUrl("http://so-unlam.net.ar").addConverterFactory(GsonConverterFactory.create()).build();
        publicacion = retrofit.create(EventsInterface.class);
    }

    public static EventsService getInstance(){
        if (instance == null)
        {
            instance = getInstanceSync();
        }
        return instance;
    }

    private static synchronized EventsService getInstanceSync(){
        if (instance == null)
        {
            instance = new EventsService();
        }
        return instance;
    }

    public void post (String token, EntidadEvents ent) {
        Call<EventsResponse> request = publicacion.publicar(ent, token);
        request.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                if(response.isSuccessful()){
                    Log.i("eventsServicio", response.body().toString());
                }
                else{
                    try {
                        Log.i("eventsServicio", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {

            }
        });
    }
}
