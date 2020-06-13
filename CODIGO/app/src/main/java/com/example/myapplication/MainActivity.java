package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isOnline()){
            Toast.makeText(this.getApplicationContext(),"No hay conexión a internet", Toast.LENGTH_LONG).show();
        }
         
        Button botonlogin = findViewById(R.id.login);
        // botonregistro.setText("string");
        botonlogin.setOnClickListener(new LogInOnClickListener(this)); //componente que escucha al widget del boton login

        Button botonregistro = findViewById(R.id.registro);
        // botonregistro.setText("string");
        botonregistro.setOnClickListener(new RegisterOnClickListenerTo(this)); //componente que escucha al widget del boton registro
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
