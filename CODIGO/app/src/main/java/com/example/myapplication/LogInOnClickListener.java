package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInOnClickListener implements View.OnClickListener{
    private AppCompatActivity mainactivity;
    TextView mail;
    TextView password;
    public LogInOnClickListener(AppCompatActivity main) {
        mainactivity = main;

        mail = mainactivity.findViewById(R.id.email);
        password = mainactivity.findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        Button login = (Button) v;
        final String mailvalue = mail.getText().toString();
        String passwordvalue = password.getText().toString();
        Log.i("main", "mailvalue: "+mailvalue);
        Log.i("main", "passwordvalue: "+passwordvalue);

        EntidadLogIn entidad = new EntidadLogIn("DEV", mailvalue, passwordvalue);

        LogInService loginsrvc = LogInService.getInstance();
        loginsrvc.post(entidad, new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
             if(response.isSuccessful()){
                Log.i("registroservicio", response.body().toString());
                LogInResponse res = response.body();
                EntidadEvents entidadEvents = new EntidadEvents("DEV", "Login", "ACTIVO", "se logueó el usuario "+mailvalue);
                EventsService eventsService = EventsService.getInstance();

                eventsService.post(res.getToken(), entidadEvents);

                Intent intent = new Intent(mainactivity, EventsActivity.class);
                intent.putExtra("token", res.getToken());
                mainactivity.startActivity(intent);
             }
             else{
                 try {
                     Log.i("registroservicio", response.errorBody().string());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 Toast.makeText(mainactivity.getApplicationContext(),"Mail o password inválidos",Toast.LENGTH_LONG).show();
             }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Log.e("registroservicio", Log.getStackTraceString(t));
            }
        });
    }

    private boolean checkFormulario(){
        if(mail.getText().toString().trim().length()==0){
            Toast.makeText(mainactivity.getApplicationContext(),"El campo nombre no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(password.getText().toString().trim().length()==0){
            Toast.makeText(mainactivity.getApplicationContext(),"El campo apellido no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}