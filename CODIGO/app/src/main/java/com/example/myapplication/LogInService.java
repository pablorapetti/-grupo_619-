package com.example.myapplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInService {
    private static LogInService instance = null;
    private LogInInterface login;

    private LogInService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://so-unlam.net.ar").addConverterFactory(GsonConverterFactory.create()).build();
        login = retrofit.create(LogInInterface.class);
    }

    public static LogInService getInstance(){
        if (instance == null)
        {
            instance = getInstanceSync();
        }
        return instance;
    }

    private static synchronized LogInService getInstanceSync(){
        if (instance == null)
        {
            instance = new LogInService();
        }
        return instance;
    }

    public void post (EntidadLogIn ent, Callback<LogInResponse> callback) {

        Call<LogInResponse> request = login.login(ent);
        request.enqueue(callback);
    }
}
