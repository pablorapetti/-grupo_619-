package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegistroInterface {
    @POST("/api/api/register")
    Call<RegistroResponse> registrar(@Body EntidadRegistro body);
}
