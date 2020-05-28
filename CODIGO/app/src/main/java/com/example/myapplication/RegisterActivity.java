package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Button botonregistro = findViewById(R.id.registrarse);
        // botonregistro.setText("string");
        botonregistro.setOnClickListener(new RegisterClickListener(this));
    }
}
