package com.example.myapplication;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroService {
    private static RegistroService instance = null;
    private RegistroInterface registro;

    private RegistroService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://so-unlam.net.ar").addConverterFactory(GsonConverterFactory.create()).build();
        registro = retrofit.create(RegistroInterface.class);
    }

    public static RegistroService getInstance(){
        if (instance == null)
        {
            instance = getInstanceSync();
        }
        return instance;
    }

    private static synchronized RegistroService getInstanceSync(){
        if (instance == null)
        {
            instance = new RegistroService();
        }
        return instance;
    }

    public void post (EntidadRegistro ent, Callback<RegistroResponse> callback) {

        Call<RegistroResponse> request = registro.registrar(ent);
        request.enqueue(callback);
    }
}
