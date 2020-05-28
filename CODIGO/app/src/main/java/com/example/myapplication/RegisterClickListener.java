package com.example.myapplication;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterClickListener implements View.OnClickListener{
    private AppCompatActivity registeractivity;
    private TextView nombre;
    private TextView apellido;
    private TextView dni;
    private TextView mail;
    private TextView password;
    private TextView comision;
    private TextView grupo;
    private Switch dev;

    public RegisterClickListener(AppCompatActivity main) {registeractivity = main;
        nombre = registeractivity.findViewById(R.id.nombre);
        apellido = registeractivity.findViewById(R.id.apellido);
        dni = registeractivity.findViewById(R.id.dni);
        mail = registeractivity.findViewById(R.id.email);
        password = registeractivity.findViewById(R.id.password);
        comision = registeractivity.findViewById(R.id.comision);
        grupo = registeractivity.findViewById(R.id.grupo);
        dev = registeractivity.findViewById(R.id.env);
    }

    @Override
    public void onClick(View v) {
        if(!checkFormulario()){
           return;
        }
        Button registro = (Button) v;

        String envvalue = dev.isChecked() ? "DEV": "TEST";
        String nombrevalue = nombre.getText().toString();
        String apellidovalue = apellido.getText().toString();
        Integer dnivalue = Integer.valueOf(dni.getText().toString());
        String mailvalue = mail.getText().toString();
        String passwordvalue = password.getText().toString();
        Integer comisionvalue = Integer.valueOf(comision.getText().toString());
        Integer grupovalue = Integer.valueOf(grupo.getText().toString());
        Log.i("main", "nombre: "+nombrevalue);
        Log.i("main", "apellido: "+apellidovalue);
        Log.i("main", "dni: "+dnivalue);
        Log.i("main", "mailvalue: "+mailvalue);
        Log.i("main", "passwordvalue: "+passwordvalue);
        Log.i("main", "comision: "+comisionvalue);
        Log.i("main", "grupo: "+grupovalue);

        EntidadRegistro entidad = new EntidadRegistro(envvalue, nombrevalue, apellidovalue, dnivalue, mailvalue, passwordvalue, comisionvalue, grupovalue);
        RegistroService registroService = RegistroService.getInstance();
        registroService.post(entidad, new Callback<RegistroResponse>() {
            @Override
            public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {
               if(response.isSuccessful()) {
                   Log.i("registroservicio", response.body().toString());
                   Toast.makeText(registeractivity.getApplicationContext(), "Usuario registrado con éxito", Toast.LENGTH_LONG).show();
                   registeractivity.finish();
               }
               else{
                   Toast.makeText(registeractivity.getApplicationContext(), "Error en la validación de datos ingresados", Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onFailure(Call<RegistroResponse> call, Throwable t) {
                Log.e("registroservicio", Log.getStackTraceString(t));
            }
        });
    }

    private boolean checkFormulario(){
        if(nombre.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo nombre no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(apellido.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo apellido no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(dni.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo dni no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(mail.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo mail no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(password.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo password no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(comision.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo comsion no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }

        if(grupo.getText().toString().trim().length()==0){
            Toast.makeText(registeractivity.getApplicationContext(),"El campo grupo no puede estar vacío",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
