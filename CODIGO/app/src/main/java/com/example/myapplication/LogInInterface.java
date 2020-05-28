package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogInInterface {
    @POST("/api/api/login")
    Call<LogInResponse> login(@Body EntidadLogIn body);
}
