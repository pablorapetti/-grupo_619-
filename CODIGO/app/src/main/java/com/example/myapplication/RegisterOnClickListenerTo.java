package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterOnClickListenerTo implements View.OnClickListener  {
    private AppCompatActivity mainactivity;
    public RegisterOnClickListenerTo (AppCompatActivity main) {mainactivity = main;}


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mainactivity, RegisterActivity.class);
        mainactivity.startActivity(intent);
    }
}
